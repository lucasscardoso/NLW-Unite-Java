package rocketseat.com.pasin.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketseat.com.pasin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String>{

	List<Attendee> findByEventId(String eventId);
	Optional<Attendee >findByEventIdAndEmail(String email, String eventId);
}
