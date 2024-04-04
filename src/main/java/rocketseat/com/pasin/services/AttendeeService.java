package rocketseat.com.pasin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import rocketseat.com.pasin.domain.attendee.Attendee;
import rocketseat.com.pasin.domain.attendee.exceptions.AttendeeAlreadyExistExceptions;
import rocketseat.com.pasin.domain.attendee.exceptions.AttendeeNotFoundException;
import rocketseat.com.pasin.domain.checkin.CheckIn;
import rocketseat.com.pasin.dto.dto.attendee.AttendeeBadgeDto;
import rocketseat.com.pasin.dto.dto.attendee.AttendeeBadgeResponseDto;
import rocketseat.com.pasin.dto.dto.attendee.AttendeeDetails;
import rocketseat.com.pasin.dto.dto.attendee.AttendeesListResponseDto;
import rocketseat.com.pasin.repositories.AttendeeRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {

	private final AttendeeRepository attendeeRepository;
	
	private final CheckInService checkInService;
	
	
	public List<Attendee> getAllAttendeesFromEvent(String eventId) {
		
		return  this.attendeeRepository.findByEventId(eventId);
		
	}
	
	public AttendeesListResponseDto getEventsAttendee(String eventId) {
		List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);
		
		List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
			Optional<CheckIn> checkIn = this.checkInService.getCheckIn(attendee.getId());
			LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
			
			return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
	}).toList();
			return new  AttendeesListResponseDto(attendeeDetailsList);
  }

	
	public void verifyAttendeeSubscription(String email, String eventId) {
		
		Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId,email);
		if(isAttendeeRegistered.isPresent()) throw new AttendeeAlreadyExistExceptions("Attendee is already registered! ");
		
		
	}
	
	 
	

	public Attendee registerAttendee (Attendee newAttendee) {
		
		this.attendeeRepository.save(newAttendee);
		return newAttendee;
	}
	
	
	public void checkInAttendee(String attendeeId) {
		Attendee attendee = this.getAttendee(attendeeId);
		this.checkInService.registerCheckIn(attendee);
		
	}
	
	
	private Attendee getAttendee(String attendeeId) {
	
		return this.attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException("Attendee not found with ID: " + attendeeId));
	}
	
	
	public AttendeeBadgeResponseDto getAttendeeBadge(String attendeeId,UriComponentsBuilder uriComponentsBuilder) {
		
		Attendee attendee = this.getAttendee(attendeeId);
		
		var uri = uriComponentsBuilder.path("/attendees/{attendeeId/check-in}").buildAndExpand(attendeeId).toUri().toString();
		
		
		
		AttendeeBadgeDto badgeDto = new AttendeeBadgeDto(attendee.getName(), attendee.getEmail(), uri, attendee.getEvent().getId());
		
		return new AttendeeBadgeResponseDto(badgeDto);
	}
	
	
	
}
