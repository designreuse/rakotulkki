package org.rakotulkki.resource.user;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.joda.time.LocalDate;
import org.rakotulkki.model.dto.CustomerDTO;
import org.rakotulkki.model.hibernate.Customer;
import org.rakotulkki.model.hibernate.Session;
import org.rakotulkki.model.hibernate.Therapist;
import org.rakotulkki.repository.CustomerRepository;
import org.rakotulkki.repository.InvoiceRepository;
import org.rakotulkki.repository.SessionRepository;
import org.rakotulkki.services.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jkuittin
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerRepository customerRepository;

	private final InvoiceRepository invoiceRepository;

	private final SessionRepository sessionRepository;

	private final MapperFacade mapperFacade;

	private final TherapistService therapistService;

	@Autowired
	public CustomerController(final CustomerRepository customerRepository, final InvoiceRepository invoiceRepository,
		final SessionRepository sessionRepository, final MapperFacade mapperFacade,
		final TherapistService therapistService) {
		this.customerRepository = customerRepository;
		this.invoiceRepository = invoiceRepository;
		this.sessionRepository = sessionRepository;
		this.mapperFacade = mapperFacade;
		this.therapistService = therapistService;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Return all therapist's customers",
				  response = CustomerDTO.class,
				  responseContainer = "List")
	public List<CustomerDTO> customers() {
		Therapist therapist = therapistService.getCurrentUser();

		final List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();

		final Iterable<Customer> customers = customerRepository.findAll();

		for (Customer c : customers) {
			CustomerDTO dto = map(c);
			customerDTOs.add(dto);
		}

		return customerDTOs;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a new customer",
				  response = CustomerDTO.class)
	public CustomerDTO create(
		@ApiParam(value = "Customer to be created", required = true) @RequestBody Customer customerDTO) {
		Therapist therapist = therapistService.getCurrentUser();

		Customer customer = mapperFacade.map(customerDTO, Customer.class);

		customer.setTherapist(therapist);
		return map(customerRepository.save(customer));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update existing customer",
				  response = CustomerDTO.class)
	public CustomerDTO update(@ApiParam(value = "Customer id", required = true) @PathVariable Long id,
		@ApiParam(value = "Customer to be updated") @RequestBody CustomerDTO customer) {
		Customer loaded = customerRepository.findOne(id);

		mapperFacade.map(customer, loaded);

		return map(customerRepository.save(loaded));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a customer")
	public void delete(@ApiParam(value = "Customer to be updated", required = true) @PathVariable Long id) {
		customerRepository.delete(id);
	}

	// Private methods

	private CustomerDTO map(final Customer c) {
		CustomerDTO dto = mapperFacade.map(c, CustomerDTO.class);
		List<Session> sessions = sessionRepository
			.findByTherapistAndInvoiceRowIsNullAndSessionDateBeforeAndCustomer(c.getTherapist(),
				LocalDate.now().plusDays(1), c);
		dto.setUninvoicedSessions(sessions.size());
		return dto;
	}

}
