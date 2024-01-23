package com.javatechie.saga.order.config;

import com.javatechie.saga.commons.event.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;


//publish to kafka message queue (msg from OrderStatusPublisher) 
/* publisher details in application.proporty
  spring:
  cloud:
    stream:
      function:
        definition : orderSupplier;paymentEventConsumer   [function names]
	  bindings:
        orderSupplier-out-0:  //out [orderSupplier] matched name to kafka  
          destination: order-event
          
        paymentEventConsumer-in-0 : // in kafka name 
          destination: payment-event
 */
@Configuration
public class OrderPublisherConfig {

    @Bean
    public Sinks.Many<OrderEvent> orderSinks(){ // OrderEvent publosh load hear
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sinks){ //in function name :orderSupplier upload the message
       return sinks::asFlux; 
    }
}
