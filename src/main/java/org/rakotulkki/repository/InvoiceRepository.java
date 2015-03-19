package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Invoice;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
