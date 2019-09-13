package tn.fakenewsdetection.commentmicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.commentmicroservice.entities.Author;
import tn.fakenewsdetection.commentmicroservice.events.models.AccountCreatedEvent;
import tn.fakenewsdetection.commentmicroservice.services.AuthorService;

@Component
public class AccountCreatedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());
	private AuthorService authorService;

	@Autowired
	AccountCreatedHandler(final AuthorService authorService) {
		this.authorService = authorService;
	}

	@RabbitListener(queues = "${account.created.queue}")
	void handleAccountCreated(final AccountCreatedEvent event) {
		log.info("Account Created Event received: {}", event.getAccountId());
		try {
			Author newAuthor = new Author(event.getAccountId(), event.getUsername());
			authorService.save(newAuthor);
		} catch (final Exception e) {
			log.error("Error when trying to process Account Created Event", e);
			// Avoid the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
