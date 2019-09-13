package tn.fakenewsdetection.badgemicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.badgemicroservice.entities.User;
import tn.fakenewsdetection.badgemicroservice.events.models.AccountUpdatedEvent;
import tn.fakenewsdetection.badgemicroservice.services.UserService;

@Component
public class AccountUpdatedHander {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private UserService userService;

	@Autowired
	AccountUpdatedHander(final UserService userService) {
		this.userService = userService;
	}

	@RabbitListener(queues = "${account.updated.queue}")
	void handleAccountUpdated(final AccountUpdatedEvent event) {
		log.info("Account Updated Event received: {}", event.getAccountId());
		try {
			User user = new User(event.getAccountId(), event.getUsername());
			userService.update(user);
		} catch (final Exception e) {
			log.error("Error when trying to process Account Updated Event", e);
			// Avoids the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
