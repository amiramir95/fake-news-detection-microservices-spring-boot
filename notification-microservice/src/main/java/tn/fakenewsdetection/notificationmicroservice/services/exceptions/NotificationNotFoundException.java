package tn.fakenewsdetection.notificationmicroservice.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotificationNotFoundException(String message) {
		super(message);
	}

}
