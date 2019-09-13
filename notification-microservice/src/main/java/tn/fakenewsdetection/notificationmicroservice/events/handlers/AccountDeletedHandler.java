package tn.fakenewsdetection.notificationmicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.notificationmicroservice.events.models.AccountDeletedEvent;
import tn.fakenewsdetection.notificationmicroservice.services.NotificationService;

@Component
public class AccountDeletedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private NotificationService notificationService;

	@Autowired
	AccountDeletedHandler(final NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RabbitListener(queues = "${account.deleted.queue}")
	void handleAccountUpdated(final AccountDeletedEvent event) {
		log.info("Account Deleted Event received: {}", event.getAccountId());
		try {
			notificationService.deleteByReceiverId(event.getAccountId());
		} catch (final Exception e) {
			log.error("Error when trying to process Account Deleted Event", e);
			// Avoids the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
