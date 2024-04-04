package rocketseat.com.pasin.dto.dto.event;

public record EventDetailDto(
		String id,
		String title,
		String details,
		String slug,
		Integer maximumAttendees,
		Integer attendeesAmouth) {

	
}
