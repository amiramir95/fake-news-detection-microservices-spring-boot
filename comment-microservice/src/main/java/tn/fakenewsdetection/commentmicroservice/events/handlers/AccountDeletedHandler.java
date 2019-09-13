package tn.fakenewsdetection.commentmicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.commentmicroservice.events.models.AccountDeletedEvent;
import tn.fakenewsdetection.commentmicroservice.services.AuthorService;

@Component
public class AccountDeletedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private AuthorService authorService;

	@Autowired
	AccountDeletedHandler(final AuthorService authorService) {
		this.authorService = authorService;
	}

	@RabbitListener(queues = "${account.deleted.queue}")
	void handleAccountDeleted(final AccountDeletedEvent event) {
		log.info("Account Deleted Event received: {}", event.getAccountId());
		try {
			authorService.delete(event.getAccountId());
		} catch (final Exception e) {
			log.error("Error when trying to process Account Deleted Event", e);
			// Avoids the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
