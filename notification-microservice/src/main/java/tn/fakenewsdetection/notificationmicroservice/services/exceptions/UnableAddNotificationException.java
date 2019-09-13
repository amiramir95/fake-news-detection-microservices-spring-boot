package tn.fakenewsdetection.notificationmicroservice.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableAddNotificationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnableAddNotificationException(String message) {
		super(message);
	}
}
