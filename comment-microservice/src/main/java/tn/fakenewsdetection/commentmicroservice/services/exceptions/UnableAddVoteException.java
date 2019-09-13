package tn.fakenewsdetection.commentmicroservice.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableAddVoteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnableAddVoteException(String message) {
		super(message);
	}

}
