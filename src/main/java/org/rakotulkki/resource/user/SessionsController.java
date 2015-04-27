package org.rakotulkki.resource.user;

import ma.glasnost.orika.MapperFacade;
import org.rakotulkki.model.dto.CalendarSessionDTO;
import org.rakotulkki.model.dto.SessionDTO;
import org.rakotulkki.model.hibernate.Session;
import org.rakotulkki.model.hibernate.Therapist;
import org.rakotulkki.repository.CustomerRepository;
import org.rakotulkki.repository.SessionRepository;
import org.rakotulkki.services.TherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jkuittin
 */
@RestController
@RequestMapping("/sessions")
public class SessionsController {

	private final SessionRepository sessionRepository;
	private final CustomerRepository customerRepository;
	private final TherapistService therapistService;

	private final MapperFacade mapper;

	@Autowired
	public SessionsController(final SessionRepository sessionRepository, final CustomerRepository customerRepository,
		final TherapistService therapistService, final MapperFacade mapper) {
		this.sessionRepository = sessionRepository;
		this.customerRepository = customerRepository;
		this.therapistService = therapistService;
		this.mapper = mapper;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<SessionDTO> sessions() {
		return mapper.mapAsList(sessionRepository.findAll(), SessionDTO.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public SessionDTO create(@RequestBody SessionDTO sessionDTO) {
		Therapist therapist = therapistService.getCurrentUser();

		Session session = mapper.map(sessionDTO, Session.class);

		// Customer is provided by id so find the corresponding Customer and set it to session
		session.setCustomer(customerRepository.findOne(sessionDTO.getCustomerId()));
		session.setTherapist(therapist);

		return mapper.map(sessionRepository.save(session), SessionDTO.class);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public SessionDTO update(@PathVariable Long id, @RequestBody SessionDTO sessionDTO) {
		Session loaded = sessionRepository.findOne(id);

		mapper.map(sessionDTO, loaded);

		return mapper.map(sessionRepository.save(loaded), SessionDTO.class);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		sessionRepository.delete(id);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "asEvents")
	public List<CalendarSessionDTO> calendarSessions() {
		Therapist therapist = therapistService.getCurrentUser();

		return mapper.mapAsList(sessionRepository.findAll(), CalendarSessionDTO.class);
	}

}
