package tn.fakenewsdetection.commentmicroservice.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VoteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VoteNotFoundException(String message) {
		super(message);
	}

}
