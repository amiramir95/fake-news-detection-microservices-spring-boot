package tn.fakenewsdetection.badgemicroservice.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableAddUserEarnedPointsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnableAddUserEarnedPointsException(String message) {
		super(message);
	}
}
