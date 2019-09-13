package tn.fakenewsdetection.notificationmicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.notificationmicroservice.entities.Notification;
import tn.fakenewsdetection.notificationmicroservice.entities.NotificationType;
import tn.fakenewsdetection.notificationmicroservice.events.models.BadgeAwardedEvent;
import tn.fakenewsdetection.notificationmicroservice.services.NotificationService;

@Component
public class BadgeAwardedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());
	private NotificationService notificationService;

	@Autowired
	public BadgeAwardedHandler(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}

	@RabbitListener(queues = "${badge.awarded.queue}")
	void handleBadgeAwarded(final BadgeAwardedEvent event) {
		log.info("Badge Awarded Event received for user : {}", event.getUserId());
		try {
			Notification newNotification = new Notification(event.getUserId(), NotificationType.BADGE_AWARDED,
					event.getBadgeId());
			notificationService.save(newNotification);
		} catch (final Exception e) {
			log.error("Error when trying to process Badge Awarded Event", e);
			// Avoid the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
