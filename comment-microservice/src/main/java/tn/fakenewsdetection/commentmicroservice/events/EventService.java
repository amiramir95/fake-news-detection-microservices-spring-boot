package tn.fakenewsdetection.commentmicroservice.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.commentmicroservice.events.models.CommentCreatedEvent;
import tn.fakenewsdetection.commentmicroservice.events.models.CommentDeletedEvent;
import tn.fakenewsdetection.commentmicroservice.events.models.ReplyCreatedEvent;
import tn.fakenewsdetection.commentmicroservice.events.models.UpvoteCreatedEvent;

@Component
public class EventService {

	@Autowired
	private EventDispatcher eventDispatcher;

	// private static Logger logger = LoggerFactory.getLogger(EventService.class);

	public void sendCommentCreatedEvent(int userId) {
		CommentCreatedEvent commentCreatedEvent = new CommentCreatedEvent(userId);
		eventDispatcher.sendCommentCreatedEvent(commentCreatedEvent);

	}

	public void sendCommentDeletedEvent(int userId) {
		CommentDeletedEvent commentDeletedEvent = new CommentDeletedEvent(userId);
		eventDispatcher.sendCommentDeletedEvent(commentDeletedEvent);
	}

	public void sendReplyCreatedEvent(int parentCommentId, int parentCommentAuthorId, String replyAuthorUsername) {
		ReplyCreatedEvent replyCreatedEvent = new ReplyCreatedEvent(parentCommentId, parentCommentAuthorId,
				replyAuthorUsername);
		eventDispatcher.sendReplyCreatedEvent(replyCreatedEvent);

	}

	public void sendUpvoteCreatedEvent(int commentId, int commentAuthorId, int upvoterId) {
		UpvoteCreatedEvent upvoteCreatedEvent = new UpvoteCreatedEvent(commentId, commentAuthorId, upvoterId);
		eventDispatcher.sendUpvoteCreatedEvent(upvoteCreatedEvent);

	}

}
