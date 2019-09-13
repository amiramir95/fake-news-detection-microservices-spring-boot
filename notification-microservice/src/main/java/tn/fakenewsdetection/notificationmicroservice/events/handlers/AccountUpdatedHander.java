package tn.fakenewsdetection.notificationmicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.notificationmicroservice.events.models.AccountUpdatedEvent;
import tn.fakenewsdetection.notificationmicroservice.services.NotificationService;

@Component
public class AccountUpdatedHander {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private NotificationService notificationService;

	@Autowired
	AccountUpdatedHander(final NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RabbitListener(queues = "${account.updated.queue}")
	void handleAccountUpdated(final AccountUpdatedEvent event) {
		log.info("Account Updated Event received: {}", event.getUsername());
		try {
			notificationService.updateSenderUsername(event.getUsername());
		} catch (final Exception e) {
			log.error("Error when trying to process Account Updated Event", e);
			// Avoids the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
