package tn.fakenewsdetection.accountmicroservice.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.accountmicroservice.events.models.AccountCreatedEvent;
import tn.fakenewsdetection.accountmicroservice.events.models.AccountDeletedEvent;
import tn.fakenewsdetection.accountmicroservice.events.models.AccountUpdatedEvent;

@Component
public class EventDispatcher {

	private RabbitTemplate rabbitTemplate;

	// The exchange to use to send anything related to Account
	private String accountExchange;

	// The routing key to use to send this particular event
	private String accountCreatedRoutingKey;

	private String accountUpdatedRoutingKey;

	private String accountDeletedRoutingKey;

	@Autowired
	EventDispatcher(final RabbitTemplate rabbitTemplate, @Value("${account.exchange}") final String accountExchange,
			@Value("${account.created.key}") final String accountCreatedRoutingKey,
			@Value("${account.updated.key}") final String accountUpdatedRoutingKey,
			@Value("${account.deleted.key}") final String accountDeletedRoutingKey)

	{
		this.rabbitTemplate = rabbitTemplate;
		this.accountExchange = accountExchange;
		this.accountCreatedRoutingKey = accountCreatedRoutingKey;
		this.accountUpdatedRoutingKey = accountUpdatedRoutingKey;
		this.accountDeletedRoutingKey = accountDeletedRoutingKey;
	}

	public void sendAccountCreatedEvent(final AccountCreatedEvent accountCreatedEvent) {
		rabbitTemplate.convertAndSend(accountExchange, accountCreatedRoutingKey, accountCreatedEvent);
	}

	public void sendAccountUpdatedEvent(final AccountUpdatedEvent accountUpdatedEvent) {
		rabbitTemplate.convertAndSend(accountExchange, accountUpdatedRoutingKey, accountUpdatedEvent);
	}

	public void sendAccountDeletedEvent(final AccountDeletedEvent accountDeletedEvent) {
		rabbitTemplate.convertAndSend(accountExchange, accountDeletedRoutingKey, accountDeletedEvent);
	}

}
