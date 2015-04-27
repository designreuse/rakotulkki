package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jkuittin
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByTherapistId(Long id);

}
