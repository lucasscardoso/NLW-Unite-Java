package rocketseat.com.pasin.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import rocketseat.com.pasin.domain.event.exceptions.EventNotFoundException;

@ControllerAdvice
public class ExceptionEntityHandler {

	@ExceptionHandler
	public ResponseEntity handlerEventNotFound(EventNotFoundException event) {
		return ResponseEntity.notFound().build();
	}
}
