package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Customer;
import org.rakotulkki.model.hibernate.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jkuittin
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

	List<Customer> findByTherapistId(Long id);

}
