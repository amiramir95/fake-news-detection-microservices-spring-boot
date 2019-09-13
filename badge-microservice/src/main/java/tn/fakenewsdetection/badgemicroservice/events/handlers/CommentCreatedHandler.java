package tn.fakenewsdetection.badgemicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.badgemicroservice.entities.Badge;
import tn.fakenewsdetection.badgemicroservice.entities.Category;
import tn.fakenewsdetection.badgemicroservice.entities.UserEarnedPoints;
import tn.fakenewsdetection.badgemicroservice.events.EventService;
import tn.fakenewsdetection.badgemicroservice.events.models.CommentCreatedEvent;
import tn.fakenewsdetection.badgemicroservice.services.BadgeService;
import tn.fakenewsdetection.badgemicroservice.services.UserEarnedPointsService;

@Component
public class CommentCreatedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private UserEarnedPointsService userEarnedPointsService;
	private BadgeService badgeService;
	private EventService eventService;

	@Autowired
	public CommentCreatedHandler(final UserEarnedPointsService userEarnedPointsService, final BadgeService badgeService,
			final EventService eventService) {
		super();
		this.userEarnedPointsService = userEarnedPointsService;
		this.badgeService = badgeService;
		this.eventService = eventService;
	}

	@RabbitListener(queues = "${comment.created.queue}")
	void handleAccountCreated(final CommentCreatedEvent event) {
		log.info("Comment Created Event received: {}", event.getUserId());
		try {

			// Add points to user
			UserEarnedPoints userEarnedPoints = userEarnedPointsService.updatePoints(event.getUserId(),
					Category.COMMENT, true);
			// Check if user has won badge and send event if he won
			Badge badge = badgeService.checkBadgeWon(userEarnedPoints);
			if (badge != null)
				eventService.sendBadgeAwardedEvent(event.getUserId(), badge.getId());

		} catch (final Exception e) {
			log.error("Error when trying to process Account Created Event", e);
			// Avoid the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
