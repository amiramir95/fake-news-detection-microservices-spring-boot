package tn.fakenewsdetection.commentmicroservice.events.models;

import java.io.Serializable;

public class CommentDeletedEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;

	public CommentDeletedEvent() {
	}

	public CommentDeletedEvent(int userId) {
		super();
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserDeletedEvent [userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentDeletedEvent other = (CommentDeletedEvent) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

}
