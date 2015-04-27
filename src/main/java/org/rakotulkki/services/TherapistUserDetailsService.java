package org.rakotulkki.services;

import ma.glasnost.orika.MapperFacade;
import org.rakotulkki.model.dto.TherapistDTO;
import org.rakotulkki.repository.TherapistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author jkuittin
 */
@Service
public class TherapistUserDetailsService implements UserDetailsService {

	private final TherapistRepository therapistRepository;

	private final MapperFacade mapper;

	@Autowired
	public TherapistUserDetailsService(final TherapistRepository therapistRepository, final MapperFacade mapper) {
		this.therapistRepository = therapistRepository;
		this.mapper = mapper;
	}

	@Override
	public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
		TherapistDTO user = mapper.map(therapistRepository.findByEmail(s), TherapistDTO.class);
		return user;
	}
}
