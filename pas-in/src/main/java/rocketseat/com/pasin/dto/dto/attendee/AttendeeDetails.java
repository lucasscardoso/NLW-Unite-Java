package rocketseat.com.pasin.dto.dto.attendee;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;


public record AttendeeDetails(String id, String name, String email, LocalDateTime createdAt, LocalDateTime checkInAt) {

	
}
