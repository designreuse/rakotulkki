package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
