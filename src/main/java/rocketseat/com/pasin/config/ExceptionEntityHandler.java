package rocketseat.com.pasin.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import rocketseat.com.pasin.domain.attendee.exceptions.AttendeeAlreadyExistExceptions;
import rocketseat.com.pasin.domain.attendee.exceptions.AttendeeNotFoundException;
import rocketseat.com.pasin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import rocketseat.com.pasin.domain.event.exceptions.EventFullException;
import rocketseat.com.pasin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.pasin.dto.dto.general.ErrorResponseDto;

@ControllerAdvice
public class ExceptionEntityHandler {

	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity handlerEventNotFound(EventNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(EventFullException.class)
	public ResponseEntity<ErrorResponseDto> handlerEventFullException(EventFullException exception) {
		return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
	}
	
	@ExceptionHandler(AttendeeNotFoundException.class)
	public ResponseEntity handlerAttendeeNotFound(AttendeeNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(AttendeeAlreadyExistExceptions.class)
	public ResponseEntity handlerAttendeeAlreadyExist(AttendeeAlreadyExistExceptions exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@ExceptionHandler(CheckInAlreadyExistsException.class)
	public ResponseEntity handlerCheckInAlreadyExists(CheckInAlreadyExistsException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
}
