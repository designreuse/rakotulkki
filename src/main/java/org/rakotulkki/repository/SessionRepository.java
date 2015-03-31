package org.rakotulkki.repository;

import org.joda.time.LocalDate;
import org.rakotulkki.model.hibernate.Customer;
import org.rakotulkki.model.hibernate.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jkuittin
 */
public interface SessionRepository extends CrudRepository<Session, Long> {

	List<Session> findByInvoiceRowIsNullAndSessionDateBefore(final LocalDate date);

	List<Session> findByInvoiceRowIsNullAndSessionDateBeforeAndCustomer(final LocalDate date, final Customer customer);
}
