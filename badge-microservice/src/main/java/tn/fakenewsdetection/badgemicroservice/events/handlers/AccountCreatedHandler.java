package tn.fakenewsdetection.badgemicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.badgemicroservice.entities.Category;
import tn.fakenewsdetection.badgemicroservice.entities.User;
import tn.fakenewsdetection.badgemicroservice.entities.UserEarnedPoints;
import tn.fakenewsdetection.badgemicroservice.events.models.AccountCreatedEvent;
import tn.fakenewsdetection.badgemicroservice.services.UserEarnedPointsService;
import tn.fakenewsdetection.badgemicroservice.services.UserService;

@Component
public class AccountCreatedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private UserEarnedPointsService userPointCategoryService;

	private UserService userService;

	@Autowired
	public AccountCreatedHandler(final UserEarnedPointsService userPointCategoryService,
			final UserService userService) {
		this.userPointCategoryService = userPointCategoryService;
		this.userService = userService;
	}

	@RabbitListener(queues = "${account.created.queue}")
	void handleAccountCreated(final AccountCreatedEvent event) {
		log.info("Account Created Event received: {}", event.getAccountId());
		try {
			// Add User Record
			User user = new User(event.getAccountId(), event.getUsername());
			User newUser = userService.save(user);
			// Add UserEarnedPoints records for each category
			for (Category category : Category.values()) {
				UserEarnedPoints userPointCategory = new UserEarnedPoints(newUser, category, 0);
				userPointCategoryService.save(userPointCategory);
			}

		} catch (final Exception e) {
			log.error("Error when trying to process Account Created Event", e);
			// Avoid the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
