package services.simulateurConfiguration;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by Matthieu on 18/01/2018.
 */
@SpringBootApplication
public class SimulationWebService {

    public final static String SIMULATEUR_QUEUE = "simulateur-queue";
    public static void main(String[] args) {
        SpringApplication.run(SimulationWebService.class, args);
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
    Queue queueSimulateur() {
        return new Queue(SIMULATEUR_QUEUE, false);
    }

    @Bean
    TopicExchange exchangeSimulateur(){
        return new TopicExchange("facade-to-simu");
    }

    @Bean
    Binding bindingSimulateur(@Qualifier("queueSimulateur") Queue queue, @Qualifier("exchangeSimulateur")TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SIMULATEUR_QUEUE);
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
    MessageListenerAdapter listenerAdapterSimulateur(SimulateurWebController receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessageFromFacade");
    }
}
