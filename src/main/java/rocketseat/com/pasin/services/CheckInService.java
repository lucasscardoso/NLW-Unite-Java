package rocketseat.com.pasin.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rocketseat.com.pasin.domain.attendee.Attendee;
import rocketseat.com.pasin.domain.checkin.CheckIn;
import rocketseat.com.pasin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import rocketseat.com.pasin.repositories.CheckInRepository;

@Service
@RequiredArgsConstructor
public class CheckInService {

	private final CheckInRepository checkInRepository;
	
	
	public void registerCheckIn(Attendee attendee) {
		
		this.verifyCheckInExists(attendee.getId());
		
		CheckIn newcheckin = new CheckIn();
		newcheckin.setAttendee(attendee);
		newcheckin.setCreatedAt(LocalDateTime.now());
		
		this.checkInRepository.save(newcheckin);
	}
	
	private void verifyCheckInExists(String attendeeId) {
		Optional<CheckIn> isCheckIn = this.getCheckIn(attendeeId);
		
		if(isCheckIn.isPresent()) throw new CheckInAlreadyExistsException("Attendee Already Checked In!");
	}
	
	
	public Optional<CheckIn> getCheckIn(String attendeeId){
		
		return this.checkInRepository.findByAttendeeId(attendeeId);
		
	}
}
