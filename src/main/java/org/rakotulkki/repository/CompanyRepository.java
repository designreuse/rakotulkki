package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Therapist;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface CompanyRepository extends CrudRepository<Therapist, Long> {

}
