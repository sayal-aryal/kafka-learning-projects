package com.myproject.ws.products;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createTopic(){
        return TopicBuilder.name("product-created-events-topic")
                .partitions(3)
                .replicas(3) //Set the replica according to the server/broker in my case I have 3 servers/brokers so I set the replica 3
                .configs(Map.of("min.insync.replicas","2")) // minimum number of replica that much acknowledge write operation to consider write operation successful
                .build();
    }
}
