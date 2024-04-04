package rocketseat.com.pasin.domain.event.exceptions;

public class EventNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EventNotFoundException(String msg) {
		super(msg);
	}
}
