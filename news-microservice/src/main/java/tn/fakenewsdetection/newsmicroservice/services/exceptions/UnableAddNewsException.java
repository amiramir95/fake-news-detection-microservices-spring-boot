package tn.fakenewsdetection.newsmicroservice.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableAddNewsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnableAddNewsException(String message) {
		super(message);
	}

}
