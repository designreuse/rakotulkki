package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.InvoiceAudit;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface InvoiceAuditRepository extends CrudRepository<InvoiceAudit, Long> {

}
