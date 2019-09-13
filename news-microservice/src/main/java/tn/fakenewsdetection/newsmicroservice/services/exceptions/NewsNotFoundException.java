package tn.fakenewsdetection.newsmicroservice.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NewsNotFoundException(String message) {
		super(message);
	}

}
