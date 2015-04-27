package org.rakotulkki.repository;

import org.rakotulkki.model.hibernate.Therapist;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jkuittin
 */
public interface TherapistRepository extends CrudRepository<Therapist, Long> {

	public Therapist findByEmail(final String email);

}
