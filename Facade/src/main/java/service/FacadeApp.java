package service;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import javax.sound.midi.Receiver;

/**
 * Created by Jeremy on 18/01/2018.
 */

@Configuration
@org.springframework.boot.autoconfigure.SpringBootApplication
public class FacadeApp {

    public final static String CONFIG_QUEUE = "config-queue";
    public final static String SIMULATEUR_QUEUE = "simulateur-queue";
    public static void main(String[] args) {
        SpringApplication.run(FacadeApp.class, args);
    }

    @Bean
    public FilterRegistrationBean remoteAddressFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        RemoteAddrFilter filter = new RemoteAddrFilter();
        filter.setAllow("127.0.0.1");
        //filter.setAllow("0:0:0:0:0:0:0:1");
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }


    @Bean
    Queue queueConfig() {
        return new Queue(CONFIG_QUEUE, false);
    }

    @Bean
    Queue queueSimulateur() {
        return new Queue(SIMULATEUR_QUEUE, false);
    }

    @Bean
    TopicExchange exchangeConfig() {
        return new TopicExchange("config-exchange");
    }

    @Bean
    TopicExchange exchangeSimulateur(){
        return new TopicExchange("simu-to-facade");
    }

    @Bean
    Binding bindingConfig(@Qualifier("queueConfig") Queue queue, @Qualifier("exchangeConfig") TopicExchange exchange) {
        return BindingBuilder.bind(queueConfig()).to(exchangeConfig()).with(CONFIG_QUEUE);
    }

    @Bean
    Binding bindingSimulateur(@Qualifier("queueSimulateur") Queue queue, @Qualifier("exchangeSimulateur")TopicExchange exchange) {
        return BindingBuilder.bind(queueSimulateur()).to(exchangeSimulateur()).with(SIMULATEUR_QUEUE);
    }


    @Bean
    SimpleMessageListenerContainer containerConfig(ConnectionFactory connectionFactory,
                                                   @Qualifier("listenerAdapterConfig")  MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(CONFIG_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerSimulateur(ConnectionFactory connectionFactory,
                                                       @Qualifier("listenerAdapterSimulateur") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SIMULATEUR_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapterConfig(FacadeController receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessageFromConfig");
    }

    @Bean
    MessageListenerAdapter listenerAdapterSimulateur(FacadeController receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessageFromSimulateur");
    }
}
