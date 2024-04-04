package rocketseat.com.pasin.dto.dto.event;

import lombok.Getter;
import rocketseat.com.pasin.domain.event.Event;

@Getter
public class EventResponseDto {

	EventDetailDto event;
	
	public EventResponseDto(Event event, Integer numberOfAttendees) {
		
		this.event = new EventDetailDto(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), numberOfAttendees);
	}
}
