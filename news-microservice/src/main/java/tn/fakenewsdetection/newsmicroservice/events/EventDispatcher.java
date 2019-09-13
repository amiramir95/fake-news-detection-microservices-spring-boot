package tn.fakenewsdetection.newsmicroservice.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {

	private RabbitTemplate rabbitTemplate;

	// The exchange to use to send anything related to News
	private String newsExchange;

	// The routing key to use to send this particular event
	private String newsDeletedRoutingKey;

	@Autowired
	EventDispatcher(final RabbitTemplate rabbitTemplate, @Value("${news.exchange}") final String newsExchange,
			@Value("${news.deleted.key}") final String newsDeletedRoutingKey) {
		this.rabbitTemplate = rabbitTemplate;
		this.newsExchange = newsExchange;
		this.newsDeletedRoutingKey = newsDeletedRoutingKey;
	}

	public void sendNewsDeletedEvent(final NewsDeletedEvent newsDeletedEvent) {
		rabbitTemplate.convertAndSend(newsExchange, newsDeletedRoutingKey, newsDeletedEvent);
	}

}
