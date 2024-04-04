package rocketseat.com.pasin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import rocketseat.com.pasin.dto.dto.attendee.AttendeeIdDto;
import rocketseat.com.pasin.dto.dto.attendee.AttendeeRequestDto;
import rocketseat.com.pasin.dto.dto.attendee.AttendeesListResponseDto;
import rocketseat.com.pasin.dto.dto.event.EventIdDto;
import rocketseat.com.pasin.dto.dto.event.EventRequestDto;
import rocketseat.com.pasin.dto.dto.event.EventResponseDto;
import rocketseat.com.pasin.services.AttendeeService;
import rocketseat.com.pasin.services.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	private final AttendeeService attendeeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<EventResponseDto> getEvent(@PathVariable String id){
		EventResponseDto event = this.eventService.getEventDetail(id);
		
		return ResponseEntity.ok(event);
		 
	}
	
	@PostMapping
	public ResponseEntity<EventIdDto> createEvent(@RequestBody EventRequestDto body, UriComponentsBuilder uriComponentsBuilder){
		EventIdDto  eventIdDto = this.eventService.createEvent(body);
		var uri = uriComponentsBuilder.path("/events/{}id").buildAndExpand(eventIdDto.eventId()).toUri();
		
		return ResponseEntity.created(uri).body(eventIdDto);
	}
	
	@PostMapping("/{eventId}/attendees")
	public ResponseEntity<AttendeeIdDto> registerParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDto body, UriComponentsBuilder uriComponentsBuilder){
		AttendeeIdDto attendeeIdDto = this.eventService.registerAttendeeOnEvent(eventId,body);
		var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDto.attendeeId()).toUri();
		
		return ResponseEntity.created(uri).body(attendeeIdDto);
	}
	
	
	@GetMapping("/attendees/{id}")
	public ResponseEntity<AttendeesListResponseDto> getEventAttendees(@PathVariable String id){
		AttendeesListResponseDto attendeeListResponse = this.attendeeService.getEventsAttendee(id);
		
		return ResponseEntity.ok(attendeeListResponse);
		 
	}
	
}
