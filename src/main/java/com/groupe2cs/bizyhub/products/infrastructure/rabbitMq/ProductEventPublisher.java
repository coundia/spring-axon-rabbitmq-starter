package com.groupe2cs.bizyhub.products.infrastructure.rabbitMq;


import com.groupe2cs.bizyhub.products.application.CommandHandler.ProductCommandHandler;
import com.groupe2cs.bizyhub.products.application.event.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProductEventPublisher {

    private final RabbitTemplate rabbitTemplate;


    public ProductEventPublisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }


    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){

        Logger.getLogger(ProductCommandHandler.class.getName()).info("Publishing event: " + productCreatedEvent);

        rabbitTemplate.convertAndSend("axon-exchange","product.created",productCreatedEvent);
    }
}
