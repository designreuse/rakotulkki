package org.rakotulkki.services;

import org.rakotulkki.model.hibernate.Therapist;
import org.rakotulkki.repository.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author jkuittin
 */
@Component
class TherapistServiceImpl implements TherapistService {

	private final TherapistRepository therapistRepository;

	@Autowired
	TherapistServiceImpl(final TherapistRepository therapistRepository) {
		this.therapistRepository = therapistRepository;
	}

	@Override
	public Therapist getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return therapistRepository.findByEmail(authentication.getName());
	}
}
