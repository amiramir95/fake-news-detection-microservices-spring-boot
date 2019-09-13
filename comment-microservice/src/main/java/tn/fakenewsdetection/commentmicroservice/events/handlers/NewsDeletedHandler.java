package tn.fakenewsdetection.commentmicroservice.events.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.commentmicroservice.events.models.NewsDeletedEvent;
import tn.fakenewsdetection.commentmicroservice.services.CommentService;

@Component
public class NewsDeletedHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private CommentService commentService;

	@Autowired
	NewsDeletedHandler(final CommentService commentService) {
		this.commentService = commentService;
	}

	@RabbitListener(queues = "${news.deleted.queue}")
	void handleNewsDeleted(final NewsDeletedEvent event) {
		log.info("News Deleted Event received: {}", event.getNewsId());
		try {
			commentService.deleteByNewsId(event.getNewsId());
		} catch (final Exception e) {
			log.error("Error when trying to process News Deleted Event", e);
			// Avoids the event to be re-queued and reprocessed.
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

}
