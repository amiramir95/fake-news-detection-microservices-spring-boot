package tn.fakenewsdetection.commentmicroservice.events.models;

import java.io.Serializable;

public class UpvoteCreatedEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int commentId;

	private int commentAuthorId;

	private int upvoterId;

	public UpvoteCreatedEvent() {

	}

	public UpvoteCreatedEvent(int commentId, int commentAuthorId, int upvoterId) {
		super();
		this.commentId = commentId;
		this.commentAuthorId = commentAuthorId;
		this.upvoterId = upvoterId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getCommentAuthorId() {
		return commentAuthorId;
	}

	public void setCommentAuthorId(int commentAuthorId) {
		this.commentAuthorId = commentAuthorId;
	}

	public int getUpvoterId() {
		return upvoterId;
	}

	public void setUpvoterId(int upvoterId) {
		this.upvoterId = upvoterId;
	}

	@Override
	public String toString() {
		return "UpvoteCreatedEvent [commentId=" + commentId + ", commentAuthorId=" + commentAuthorId + ", upvoterId="
				+ upvoterId + "]";
	}

}
