package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Payment;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
