package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Customer;
import org.rakotulkki.model.hibernate.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jkuittin
 */
public interface PaymentRepository extends CrudRepository<Payment, Long> {

	List<Customer> findByTherapistId(Long id);
}
