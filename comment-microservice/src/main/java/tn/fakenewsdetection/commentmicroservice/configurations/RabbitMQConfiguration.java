package tn.fakenewsdetection.commentmicroservice.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

	// Configuration for producing events
	
	@Bean
	public TopicExchange commentExchange(@Value("${comment.exchange}") final String exchangeName) {
		return new TopicExchange(exchangeName);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	// Configuration for consuming events
	@Bean(name = "accountExchange")
	public TopicExchange accountExchange(@Value("${account.exchange}") final String exchangeName) {
		return new TopicExchange(exchangeName);
	}

	@Bean(name = "newsExchange")
	public TopicExchange newsExchange(@Value("${news.exchange}") final String exchangeName) {
		return new TopicExchange(exchangeName);
	}

	@Bean(name = "accountCreatedQueue")
	public Queue accountCreatedQueue(@Value("${account.created.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "accountUpdatedQueue")
	public Queue accountUpdatedQueue(@Value("${account.updated.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "accountDeletedQueue")
	public Queue accountDeletedQueue(@Value("${account.deleted.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "newsDeletedQueue")
	public Queue newsDeletedQueue(@Value("${news.deleted.queue}") final String queueName) {
		return new Queue(queueName, true);
	}

	@Bean(name = "commentAccountCreatedBinding")
	Binding commentAccountCreatedBinding(@Qualifier("accountCreatedQueue") final Queue queue,
			@Qualifier("accountExchange") final TopicExchange exchange,
			@Value("${account.created.key}") final String accountCreatedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(accountCreatedRoutingKey);
	}

	@Bean(name = "commentAccountUpdatedBinding")
	Binding commentAccountUpdatedBinding(@Qualifier("accountUpdatedQueue") final Queue queue,
			@Qualifier("accountExchange") final TopicExchange exchange,
			@Value("${account.updated.key}") final String accountUpdatedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(accountUpdatedRoutingKey);
	}

	@Bean(name = "commentAccountDeletedBinding")
	Binding commentAccountDeletedBinding(@Qualifier("accountDeletedQueue") final Queue queue,
			@Qualifier("accountExchange") final TopicExchange exchange,
			@Value("${account.deleted.key}") final String accountDeletedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(accountDeletedRoutingKey);
	}

	@Bean(name = "commentNewsDeletedBinding")
	Binding commentNewsDeletedBinding(@Qualifier("newsDeletedQueue") final Queue queue,
			@Qualifier("newsExchange") final TopicExchange exchange,
			@Value("${news.deleted.key}") final String newsDeletedRoutingKey) {
		return BindingBuilder.bind(queue).to(exchange).with(newsDeletedRoutingKey);
	}

	// ###################### ##############################################"
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
