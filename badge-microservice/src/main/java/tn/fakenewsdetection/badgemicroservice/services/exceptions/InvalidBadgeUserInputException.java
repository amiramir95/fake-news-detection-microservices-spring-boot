package tn.fakenewsdetection.badgemicroservice.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidBadgeUserInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidBadgeUserInputException(String message) {
		super(message);
	}
}
