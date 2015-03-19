package org.rakotulkki.resource.user;

import org.rakotulkki.model.hibernate.Customer;
import org.rakotulkki.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jkuittin
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerController(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Customer> customers() {
		return customerRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Customer create(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Customer update(@PathVariable Long id, @RequestBody Customer customer) {
		Customer loaded = customerRepository.findOne(id);
		loaded.setFirstName(customer.getFirstName());
		loaded.setLastName(customer.getLastName());
		loaded.setPrice(customer.getPrice());
		loaded.setStreet(customer.getStreet());
		loaded.setZip(customer.getZip());
		loaded.setCity(customer.getCity());
		return customerRepository.save(loaded);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		customerRepository.delete(id);
	}

}
