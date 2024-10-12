package com.myproject.ws.products.service;

import com.myproject.ws.products.rest.CreateProductRestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductRestModel productRestModel) {
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, productRestModel.getTitle(), productRestModel.getPrice(), productRestModel.getQuantity());
        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);

        future.whenComplete((result, exception) -> {
            if (exception != null) {
                LOGGER.error("***** Failed to send message: " + exception.getMessage());
            } else {
                LOGGER.info("***** Message sent successfully: " + result.getRecordMetadata());
            }
        });
        LOGGER.info("***** Retuning product id");
        //future.join(); //this method block the current thread until the future thread is complete. if we want to wait asynchronous communication to complete then we can use join.Basically It becomes synchronous.
        return productId;
    }
}
