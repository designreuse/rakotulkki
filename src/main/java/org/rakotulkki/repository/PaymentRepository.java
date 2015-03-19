package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Customer;
import org.springframework.data.repository.Repository;

/**
 * @author jkuittin
 */
public interface PaymentRepository extends Repository<Customer, Long> {

}
