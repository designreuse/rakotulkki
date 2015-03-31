package org.rakotulkki.resource.user;

import ma.glasnost.orika.MapperFacade;
import org.joda.time.LocalDate;
import org.rakotulkki.model.dto.CustomerDTO;
import org.rakotulkki.model.hibernate.Customer;
import org.rakotulkki.model.hibernate.Session;
import org.rakotulkki.repository.CustomerRepository;
import org.rakotulkki.repository.InvoiceRepository;
import org.rakotulkki.repository.SessionRepository;
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

	@Autowired
	public CustomerController(final CustomerRepository customerRepository, final InvoiceRepository invoiceRepository,
		final SessionRepository sessionRepository, final MapperFacade mapperFacade) {
		this.customerRepository = customerRepository;
		this.invoiceRepository = invoiceRepository;
		this.sessionRepository = sessionRepository;
		this.mapperFacade = mapperFacade;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<CustomerDTO> customers() {
		final List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();

		final Iterable<Customer> customers = customerRepository.findAll();

		for (Customer c : customers) {
			CustomerDTO dto = map(c);
			customerDTOs.add(dto);
		}

		return customerDTOs;
	}

	private CustomerDTO map(final Customer c) {
		CustomerDTO dto = mapperFacade.map(c, CustomerDTO.class);
		List<Session> sessions = sessionRepository
			.findByInvoiceRowIsNullAndSessionDateBeforeAndCustomer(LocalDate.now().plusDays(1), c);
		dto.setUninvoicedSessions(sessions.size());
		return dto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public CustomerDTO create(@RequestBody Customer customerDTO) {
		Customer customer = mapperFacade.map(customerDTO, Customer.class);
		return map(customerRepository.save(customer));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO customer) {
		Customer loaded = customerRepository.findOne(id);

		mapperFacade.map(customer, loaded);

		return map(customerRepository.save(loaded));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		customerRepository.delete(id);
	}

}
