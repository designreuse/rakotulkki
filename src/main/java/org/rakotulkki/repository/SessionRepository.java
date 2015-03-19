package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Session;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface SessionRepository extends CrudRepository<Session, Long> {

}
