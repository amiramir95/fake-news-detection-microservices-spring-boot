package tn.fakenewsdetection.badgemicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.badgemicroservice.entities.User;
import tn.fakenewsdetection.badgemicroservice.events.models.AccountDeletedEvent;
import tn.fakenewsdetection.badgemicroservice.services.UserEarnedPointsService;
import tn.fakenewsdetection.badgemicroservice.services.UserService;

@Component
public class AccountDeletedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private UserService userService;

	@Autowired
	public AccountDeletedHandler(final UserService userService, final UserEarnedPointsService userEarnedPointsService) {
		this.userService = userService;
	}

	@RabbitListener(queues = "${account.deleted.queue}")
	void handleAccountDeleted(final AccountDeletedEvent event) {
		log.info("Account Deleted Event received: {}", event.getAccountId());
		try {
			User user = userService.find(event.getAccountId());
			user.remove();
			userService.delete(event.getAccountId());
		} catch (final Exception e) {
			log.error("Error when trying to process Account Deleted Event", e);
			// Avoids the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
