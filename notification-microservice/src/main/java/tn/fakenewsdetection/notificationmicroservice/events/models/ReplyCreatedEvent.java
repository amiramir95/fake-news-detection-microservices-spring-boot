package tn.fakenewsdetection.notificationmicroservice.events.models;

import java.io.Serializable;

public class ReplyCreatedEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int parentCommentId;

	private int parentCommentAuthorId;

	private String replyAuthorUsername;

	public ReplyCreatedEvent() {

	}

	public ReplyCreatedEvent(int parentCommentId, int parentCommentAuthorId, String replyAuthorUsername) {
		super();
		this.parentCommentId = parentCommentId;
		this.parentCommentAuthorId = parentCommentAuthorId;
		this.replyAuthorUsername = replyAuthorUsername;
	}

	public int getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(int parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public int getParentCommentAuthorId() {
		return parentCommentAuthorId;
	}

	public void setParentCommentAuthorId(int parentCommentAuthorId) {
		this.parentCommentAuthorId = parentCommentAuthorId;
	}

	public String getReplyAuthorUsername() {
		return replyAuthorUsername;
	}

	public void setReplyAuthorUsername(String replyAuthorUsername) {
		this.replyAuthorUsername = replyAuthorUsername;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + parentCommentAuthorId;
		result = prime * result + parentCommentId;
		result = prime * result + ((replyAuthorUsername == null) ? 0 : replyAuthorUsername.hashCode());
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
		ReplyCreatedEvent other = (ReplyCreatedEvent) obj;
		if (parentCommentAuthorId != other.parentCommentAuthorId)
			return false;
		if (parentCommentId != other.parentCommentId)
			return false;
		if (replyAuthorUsername == null) {
			if (other.replyAuthorUsername != null)
				return false;
		} else if (!replyAuthorUsername.equals(other.replyAuthorUsername))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReplyCreatedEvent [parentCommentId=" + parentCommentId + ", parentCommentAuthorId="
				+ parentCommentAuthorId + ", replyAuthorUsername=" + replyAuthorUsername + "]";
	}

}
