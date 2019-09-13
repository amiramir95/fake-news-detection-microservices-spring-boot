package tn.fakenewsdetection.badgemicroservice.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.badgemicroservice.events.models.BadgeAwardedEvent;

@Component
public class EventDispatcher {

	private RabbitTemplate rabbitTemplate;

	// The exchange to use to send anything related to Badge
	private String badgeExchange;

	// The routing key to use to send this particular event
	private String badgeAwardedRoutingKey;

	@Autowired
	EventDispatcher(final RabbitTemplate rabbitTemplate, @Value("${badge.exchange}") final String badgeExchange,
			@Value("${badge.awarded.key}") final String badgeAwardedRoutingKey) {

		this.rabbitTemplate = rabbitTemplate;
		this.badgeExchange = badgeExchange;
		this.badgeAwardedRoutingKey = badgeAwardedRoutingKey;
	}

	public void sendBadgeAwardedEvent(final BadgeAwardedEvent badgeAwardedEvent) {
		rabbitTemplate.convertAndSend(badgeExchange, badgeAwardedRoutingKey, badgeAwardedEvent);
	}

}
