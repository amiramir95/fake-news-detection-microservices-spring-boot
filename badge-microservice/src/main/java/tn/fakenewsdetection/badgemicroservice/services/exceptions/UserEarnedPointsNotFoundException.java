package tn.fakenewsdetection.badgemicroservice.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserEarnedPointsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserEarnedPointsNotFoundException(String message) {
		super(message);
	}

}
