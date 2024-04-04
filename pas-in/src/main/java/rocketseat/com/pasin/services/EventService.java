package rocketseat.com.pasin.services;

import java.text.Normalizer;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rocketseat.com.pasin.domain.attendee.Attendee;
import rocketseat.com.pasin.domain.event.Event;
import rocketseat.com.pasin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.pasin.dto.dto.event.EventIdDto;
import rocketseat.com.pasin.dto.dto.event.EventRequestDto;
import rocketseat.com.pasin.dto.dto.event.EventResponseDto;
import rocketseat.com.pasin.repositories.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;
	private final AttendeeService attendeeService;
	
	public EventResponseDto getEventDetail(String eventId) {
		
		Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
		List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
		return new EventResponseDto(event, attendeeList.size());
	}
	
	public EventIdDto createEvent(EventRequestDto eventDto) {
		Event newEvent = new Event();
		newEvent.setTitle(eventDto.title());
		newEvent.setDetails(eventDto.details());
		newEvent.setMaximumAttendees(eventDto.maximumAttendees());
		newEvent.setSlug(createSlug(eventDto.title()));
		
		this.eventRepository.save(newEvent);
		 
		return new EventIdDto(newEvent.getId());
	}
	
	private String createSlug(String text) {
		String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
		return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
				.replaceAll("[^\\w\\s]", "")
				.replaceAll("\\s+", "-").toLowerCase();	
	}
}
