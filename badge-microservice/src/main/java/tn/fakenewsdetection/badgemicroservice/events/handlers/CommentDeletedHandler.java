package tn.fakenewsdetection.badgemicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.badgemicroservice.entities.Category;
import tn.fakenewsdetection.badgemicroservice.events.models.CommentDeletedEvent;
import tn.fakenewsdetection.badgemicroservice.services.UserEarnedPointsService;

@Component
public class CommentDeletedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private UserEarnedPointsService userEarnedPointsService;

	@Autowired
	CommentDeletedHandler(final UserEarnedPointsService userEarnedPointsService) {
		this.userEarnedPointsService = userEarnedPointsService;
	}

	@RabbitListener(queues = "${comment.deleted.queue}")
	void handleAccountDeleted(final CommentDeletedEvent event) {
		log.info("Comment Deleted Event received: {}", event.getUserId());
		try {

			// Substract points from user
			userEarnedPointsService.updatePoints(event.getUserId(), Category.COMMENT, false);

		} catch (final Exception e) {
			log.error("Error when trying to process Account Deleted Event", e);
			// Avoids the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
