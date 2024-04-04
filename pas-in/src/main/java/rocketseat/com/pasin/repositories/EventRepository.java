package rocketseat.com.pasin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rocketseat.com.pasin.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String>{

}
