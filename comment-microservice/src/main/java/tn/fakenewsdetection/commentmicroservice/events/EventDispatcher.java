package tn.fakenewsdetection.commentmicroservice.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.commentmicroservice.events.models.CommentCreatedEvent;
import tn.fakenewsdetection.commentmicroservice.events.models.CommentDeletedEvent;
import tn.fakenewsdetection.commentmicroservice.events.models.ReplyCreatedEvent;
import tn.fakenewsdetection.commentmicroservice.events.models.UpvoteCreatedEvent;

@Component
public class EventDispatcher {

	private RabbitTemplate rabbitTemplate;

	// The exchange to use to send anything related to Comment
	private String commentExchange;

	// The routing key to use to send this particular event
	private String commentCreatedRoutingKey;

	private String commentDeletedRoutingKey;

	private String replyCreatedRoutingkey;

	private String upvoteCreatedRoutingkey;

	@Autowired
	EventDispatcher(final RabbitTemplate rabbitTemplate, @Value("${comment.exchange}") final String commentExchange,
			@Value("${comment.created.key}") final String commentCreatedRoutingKey,
			@Value("${comment.deleted.key}") final String commentDeletedRoutingKey,
			@Value("${reply.created.key}") final String replyCreatedRoutingKey,
			@Value("${upvote.created.key}") final String upvoteCreatedRoutingKey)

	{
		this.rabbitTemplate = rabbitTemplate;
		this.commentExchange = commentExchange;
		this.commentCreatedRoutingKey = commentCreatedRoutingKey;
		this.commentDeletedRoutingKey = commentDeletedRoutingKey;
		this.replyCreatedRoutingkey = replyCreatedRoutingKey;
		this.upvoteCreatedRoutingkey = upvoteCreatedRoutingKey;
	}

	public void sendCommentCreatedEvent(final CommentCreatedEvent commentCreatedEvent) {
		rabbitTemplate.convertAndSend(commentExchange, commentCreatedRoutingKey, commentCreatedEvent);
	}

	public void sendCommentDeletedEvent(final CommentDeletedEvent commentDeletedEvent) {
		rabbitTemplate.convertAndSend(commentExchange, commentDeletedRoutingKey, commentDeletedEvent);
	}

	public void sendReplyCreatedEvent(final ReplyCreatedEvent replyCreatedEvent) {
		rabbitTemplate.convertAndSend(commentExchange, replyCreatedRoutingkey, replyCreatedEvent);
	}

	public void sendUpvoteCreatedEvent(final UpvoteCreatedEvent upvoteCreatedEvent) {
		rabbitTemplate.convertAndSend(commentExchange, upvoteCreatedRoutingkey, upvoteCreatedEvent);
	}

}
