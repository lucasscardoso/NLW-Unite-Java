package rocketseat.com.pasin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketseat.com.pasin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String>{

	List<Attendee> findByEventId(String eventId);
	
}
