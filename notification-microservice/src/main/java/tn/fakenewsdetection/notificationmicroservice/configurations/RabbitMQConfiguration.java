package tn.fakenewsdetection.notificationmicroservice.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

	// Configuration for consuming events from account microservice
	@Bean(name = "accountExchange")
	public TopicExchange accountExchange(@Value("${account.exchange}") final String exchangeName) {
		return new TopicExchange(exchangeName);
	}

	
	@Bean(name = "accountUpdatedQueue")
	public Queue accountUpdatedQueue(@Value("${account.updated.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "notificationAccountUpdatedBinding")
	Binding notificationAccountUpdatedBinding(@Qualifier("accountUpdatedQueue") final Queue queue,
			@Qualifier("accountExchange") final TopicExchange exchange,
			@Value("${account.updated.key}") final String accountUpdatedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(accountUpdatedRoutingKey);
	}
	
	@Bean(name = "accountDeletedQueue")
	public Queue accountDeletedQueue(@Value("${account.deleted.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "notificationAccountDeletedBinding")
	Binding notificationAccountDeletedBinding(@Qualifier("accountDeletedQueue") final Queue queue,
			@Qualifier("accountExchange") final TopicExchange exchange,
			@Value("${account.deleted.key}") final String accountDeletedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(accountDeletedRoutingKey);
	}

	// Configuration for consuming events from comment-microservice
	@Bean(name = "commentExchange")
	public TopicExchange commentExchange(@Value("${comment.exchange}") final String exchangeName) {
		return new TopicExchange(exchangeName);
	}

	@Bean(name = "replyCreatedQueue")
	public Queue replyCreatedQueue(@Value("${reply.created.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "notificationReplyCreatedBinding")
	Binding notificationReplyCreatedBinding(@Qualifier("replyCreatedQueue") final Queue queue,
			@Qualifier("commentExchange") final TopicExchange exchange,
			@Value("${reply.created.key}") final String replyCreatedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(replyCreatedRoutingKey);
	}

	@Bean(name = "upvoteCreatedQueue")
	public Queue upvoteCreatedQueue(@Value("${upvote.created.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "notificationUpvoteCreatedBinding")
	Binding notificationUpvoteCreatedBinding(@Qualifier("upvoteCreatedQueue") final Queue queue,
			@Qualifier("commentExchange") final TopicExchange exchange,
			@Value("${upvote.created.key}") final String upvoteCreatedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(upvoteCreatedRoutingKey);
	}

	// ###########Configuration for consuming events from badge-microservice########

	@Bean(name = "badgeExchange")
	public TopicExchange badgeExchange(@Value("${badge.exchange}") final String exchangeName) {
		return new TopicExchange(exchangeName);
	}

	@Bean(name = "badgeAwardedQueue")
	public Queue badgeAwardedQueue(@Value("${badge.awarded.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "notificationBadgeAwardedBinding")
	Binding notificationBadgeAwardedBinding(@Qualifier("badgeAwardedQueue") final Queue queue,
			@Qualifier("badgeExchange") final TopicExchange exchange,
			@Value("${badge.awarded.key}") final String badgeAwardedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(badgeAwardedRoutingKey);
	}

	////////////////
	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}

	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}

}
