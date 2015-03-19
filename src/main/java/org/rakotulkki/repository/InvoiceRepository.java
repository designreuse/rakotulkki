package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Customer;
import org.springframework.data.repository.Repository;

/**
 * @author jkuittin
 */
public interface InvoiceRepository extends Repository<Customer, Long> {
	
}
