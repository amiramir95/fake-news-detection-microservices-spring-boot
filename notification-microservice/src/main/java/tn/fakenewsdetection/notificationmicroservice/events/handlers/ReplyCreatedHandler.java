package tn.fakenewsdetection.notificationmicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.notificationmicroservice.entities.Notification;
import tn.fakenewsdetection.notificationmicroservice.entities.NotificationType;
import tn.fakenewsdetection.notificationmicroservice.events.models.ReplyCreatedEvent;
import tn.fakenewsdetection.notificationmicroservice.services.NotificationService;

@Component
public class ReplyCreatedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());
	private NotificationService notificationService;

	@Autowired
	public ReplyCreatedHandler(NotificationService notificationService) {
		super();
		this.notificationService = notificationService;
	}

	@RabbitListener(queues = "${reply.created.queue}")
	void handleReplyCreated(final ReplyCreatedEvent event) {
		log.info("Reply Created Event received for user : {}", event.getParentCommentId());
		try {

			Notification newNotification = new Notification(event.getParentCommentAuthorId(),
					event.getReplyAuthorUsername(), NotificationType.REPLY, event.getParentCommentId());
			notificationService.save(newNotification);
		} catch (final Exception e) {
			log.error("Error when trying to process Badge Awarded Event", e);
			// Avoid the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
