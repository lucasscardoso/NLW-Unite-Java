package rocketseat.com.pasin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import rocketseat.com.pasin.dto.dto.attendee.AttendeeBadgeResponseDto;
import rocketseat.com.pasin.services.AttendeeService;
import rocketseat.com.pasin.services.CheckInService;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
 
	private final AttendeeService attendeeService;

	
	@GetMapping("/{attendeeId}/badge")
	public ResponseEntity<AttendeeBadgeResponseDto> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponenteBuilder){
		
		AttendeeBadgeResponseDto response = this.attendeeService.getAttendeeBadge(attendeeId, uriComponenteBuilder);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/{attendeeId}/check-in")
	public ResponseEntity registerCheckIn(@PathVariable String attendeeId,UriComponentsBuilder uriComponentsBuilder) {
	
		this.attendeeService.checkInAttendee(attendeeId);
		
		var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
