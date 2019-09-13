package tn.fakenewsdetection.commentmicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.commentmicroservice.entities.Author;
import tn.fakenewsdetection.commentmicroservice.events.models.AccountUpdatedEvent;
import tn.fakenewsdetection.commentmicroservice.services.AuthorService;

@Component
public class AccountUpdatedHander {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private AuthorService authorService;

	@Autowired
	AccountUpdatedHander(final AuthorService authorService) {
		this.authorService = authorService;
	}

	@RabbitListener(queues = "${account.updated.queue}")
	void handleAccountUpdated(final AccountUpdatedEvent event) {
		log.info("Account Updated Event received: {}", event.getAccountId());
		try {
			Author author = new Author(event.getAccountId(), event.getUsername());
			authorService.update(author);
		} catch (final Exception e) {
			log.error("Error when trying to process Account Updated Event", e);
			// Avoids the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
