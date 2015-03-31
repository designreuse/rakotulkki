package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.InvoiceRow;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface InvoiceRowRepository extends CrudRepository<InvoiceRow, Long> {

}
