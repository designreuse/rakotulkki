package org.rakotulkki.resource.user;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import ma.glasnost.orika.MapperFacade;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.rakotulkki.model.InvoiceStatus;
import org.rakotulkki.model.dto.CompanyDTO;
import org.rakotulkki.model.dto.InvoiceDTO;
import org.rakotulkki.model.hibernate.*;
import org.rakotulkki.repository.*;
import org.rakotulkki.services.TherapistService;
import org.rakotulkki.services.jasper.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author jkuittin
 */
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	private final Logger log = LoggerFactory.getLogger(InvoiceController.class);

	private final SessionRepository sessionRepository;
	private final CustomerRepository customerRepository;
	private final InvoiceRepository invoiceRepository;
	private final InvoiceRowRepository invoiceRowRepository;
	private final InvoiceAuditRepository invoiceAuditRepository;
	private final TherapistRepository therapistRepository;
	private final TherapistService therapistService;

	private final MapperFacade mapper;

	@Autowired
	public InvoiceController(final SessionRepository sessionRepository, final CustomerRepository customerRepository,
		final InvoiceRepository invoiceRepository, final InvoiceRowRepository invoiceRowRepository,
		final InvoiceAuditRepository invoiceAuditRepository, final TherapistRepository therapistRepository,
		final TherapistService therapistService, final MapperFacade mapper) {
		this.sessionRepository = sessionRepository;
		this.customerRepository = customerRepository;
		this.invoiceRepository = invoiceRepository;
		this.invoiceRowRepository = invoiceRowRepository;
		this.invoiceAuditRepository = invoiceAuditRepository;
		this.therapistRepository = therapistRepository;
		this.therapistService = therapistService;
		this.mapper = mapper;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<InvoiceDTO> invoices() {
		return mapper.mapAsList(invoiceRepository.findAll(), InvoiceDTO.class);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "generateAll")
	public List<InvoiceDTO> generateInvoices() {
		Therapist therapist = therapistService.getCurrentUser();

		Map<Customer, List<Session>> grouped = groupSessions(sessionRepository
			.findByTherapistAndInvoiceRowIsNullAndSessionDateBefore(therapist, LocalDate.now().plusDays(1)));

		for (Customer customer : grouped.keySet()) {
			// Create invoice
			Invoice invoice = createInvoice(customer, therapist);

			for (Session session : grouped.get(customer)) {
				// Add session as a row to invoice
				addRow(invoice, session);
			}

			addAudit(invoice, invoice.getStatus());
		}

		return mapper.mapAsList(invoiceRepository.findAll(), InvoiceDTO.class);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/customer/{id}/generate")
	public List<InvoiceDTO> generateInvoices(@PathVariable Long id) {
		Therapist therapist = therapistService.getCurrentUser();

		Customer customer = customerRepository.findOne(id);

		List<Session> sessions = sessionRepository
			.findByTherapistAndInvoiceRowIsNullAndSessionDateBeforeAndCustomer(therapist, LocalDate.now().plusDays(1),
				customer);

		Invoice invoice = createInvoice(customer, therapist);

		for (Session session : sessions) {
			// Add session as a row to invoice
			addRow(invoice, session);
		}

		addAudit(invoice, invoice.getStatus());

		return mapper.mapAsList(invoiceRepository.findAll(), InvoiceDTO.class);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/customer/{id}/pending")
	public Integer calculatePending(@PathVariable Long id) {
		Therapist therapist = therapistService.getCurrentUser();

		Customer customer = customerRepository.findOne(id);

		List<Session> sessions = sessionRepository
			.findByTherapistAndInvoiceRowIsNullAndSessionDateBeforeAndCustomer(therapist, LocalDate.now().plusDays(1),
				customer);
		return sessions.size();
	}

	@RequestMapping(value = "/formPrinter", method = RequestMethod.GET)
	public void printFomr(HttpServletResponse response) throws IOException, DocumentException {

		// Create a reader to extract info
		PdfReader reader = new PdfReader("/Users/jkuittin/Downloads/KU205_W.pdf");
		PdfReader.unethicalreading = true;

		PdfStamper stamper = new PdfStamper(reader, response.getOutputStream());
		//PdfStamper stamper = new PdfStamper(reader, os);
		AcroFields form = stamper.getAcroFields();
		form.setGenerateAppearances(true);
		// Loop over the fields and get info about them
		Set<String> fields = form.getFields().keySet();
		form.renameField("pic_pankkitunniste", "tx93");

		for (String key : fields) {
			if (form.getFieldType(key) == AcroFields.FIELD_TYPE_TEXT) {
				form.setField(key, key);
			}
		}

		stamper.close();
		//reader.close();
		response.flushBuffer();
		//os.flush();
	}

	@RequestMapping(value = "/{id}/pdf", method = RequestMethod.GET)
	public void generatePdf(@PathVariable("id") Long id, HttpServletResponse response) {
		try {
			ReportService service = new ReportService();

			// Load invoice
			Invoice i = invoiceRepository.findOne(id);
			InvoiceDTO dto = mapper.map(i, InvoiceDTO.class);
			dto.setCompanyDTO(mapper.map(loadCompany(), CompanyDTO.class));

			// Print it to response's OutputStream
			service.printInvoice(dto, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream", ex);
		}

	}

	private Therapist loadCompany() {
		for (Therapist c : therapistRepository.findAll()) {
			return c;
		}
		return null;
	}

	private void addAudit(final Invoice invoice, InvoiceStatus status) {
		InvoiceAudit audit = new InvoiceAudit();
		audit.setStatus(status);
		audit.setCreated(DateTime.now());
		audit.setInvoice(invoice);

		invoiceAuditRepository.save(audit);
	}

	private void addRow(final Invoice invoice, final Session session) {
		InvoiceRow row = new InvoiceRow();
		row.setInvoice(invoice);
		row.setSession(session);
		row.setPrice(session.getPrice());
		row.setTitle("Terapiakäynti");
		row.setVat(0);
		row.setCreated(DateTime.now());
		invoiceRowRepository.save(row);
	}

	private Invoice createInvoice(final Customer customer, final Therapist therapist) {
		Invoice invoice = new Invoice();
		invoice.setCustomer(customer);
		invoice.setName(customer.getFirstName() + " " + customer.getLastName());
		invoice.setAddress(customer.getStreet());
		invoice.setZip(customer.getZip());
		invoice.setCity(customer.getCity());
		invoice.setInvoiceDate(LocalDate.now());
		invoice.setDueDate(LocalDate.now().plusDays(14));
		invoice.setCustomerNumber(String.format("%s", 1000 + customer.getId()));
		invoice.setReferenceNumber("temp");
		invoice.setInvoiceNumber(1L);
		invoice.setStatus(InvoiceStatus.NEW);
		invoice.setCreated(DateTime.now());
		invoice.setTherapist(therapist);
		invoiceRepository.save(invoice);

		invoice.setInvoiceNumber(1000L + invoice.getId());
		return invoice;
	}

	private Map<Customer, List<Session>> groupSessions(Iterable<Session> sessions) {
		Map<Customer, List<Session>> grouped = new HashMap<Customer, List<Session>>();

		for (Session session : sessions) {
			if (!grouped.containsKey(session.getCustomer())) {
				grouped.put(session.getCustomer(), new ArrayList<Session>());
			}
			grouped.get(session.getCustomer()).add(session);
		}

		return grouped;
	}

}
