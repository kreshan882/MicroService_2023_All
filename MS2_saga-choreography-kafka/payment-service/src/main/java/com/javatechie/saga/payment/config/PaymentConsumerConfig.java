package com.javatechie.saga.payment.config;

import com.javatechie.saga.commons.event.OrderEvent;
import com.javatechie.saga.commons.event.OrderStatus;
import com.javatechie.saga.commons.event.PaymentEvent;
import com.javatechie.saga.payment.service.PaymentService;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

//consiuming class (consume from orderSupplier function) | [OrderEvent] from common module
/*
 spring:
  cloud:
    stream:
      function:
        definition : paymentProcessor  //paymentProcessor() function
      bindings:
        paymentProcessor-in-0 :   // IN
          destination: order-event
        paymentProcessor-out-0:   //OUT
          destination: payment-event
 
 */

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    //public Consumer<T>m   ----> only consumer
    //public Function<T>,<T> ----> consumer-order & publisher-payment
    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment); //process in this method processPayment()
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        
    	// do and check the all logic hear ..........................
	    	// get the user id
	        // check the balance availability
	        // if balance sufficient -> Payment completed and deduct amount from DB
	        // if payment not sufficient -> cancel order event and update the amount in DB
    	
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return  Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));  // update DB, then return PaymentEvent
        }else{
            return Mono.fromRunnable(()->this.paymentService.cancelOrderEvent(orderEvent)); // update DB, then return PaymentEvent
        }
    }
}
