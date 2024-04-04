package rocketseat.com.pasin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rocketseat.com.pasin.domain.attendee.Attendee;
import rocketseat.com.pasin.domain.checkin.CheckIn;
import rocketseat.com.pasin.dto.dto.attendee.AttendeeDetails;
import rocketseat.com.pasin.dto.dto.attendee.AttendeesListResponseDto;
import rocketseat.com.pasin.repositories.AttendeeRepository;
import rocketseat.com.pasin.repositories.CheckInRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {

	private final AttendeeRepository attendeeRepository;
	private final CheckInRepository checkinRepository;
	
	public List<Attendee> getAllAttendeesFromEvent(String eventId) {
		
		return  this.attendeeRepository.findByEventId(eventId);
		
	}
	
	public AttendeesListResponseDto getEventsAttendee(String eventId) {
		List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);
		
		List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
			Optional<CheckIn> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());
			LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
			
			return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
	}).toList();
			return new  AttendeesListResponseDto(attendeeDetailsList);
  }


 
	
}
