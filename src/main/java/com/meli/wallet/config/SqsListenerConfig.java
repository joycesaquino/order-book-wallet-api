package com.meli.wallet.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class SqsListenerConfig {


    @Primary
    @Bean(name = "amazonSQSClient", destroyMethod = "shutdown")
    public AmazonSQSAsync amazonSqsClient() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        final SimpleMessageListenerContainer msgListenerContainer =
                messageListenerContainerFactory().createSimpleMessageListenerContainer();
        msgListenerContainer.setMessageHandler(messageHandler());

        return msgListenerContainer;
    }

    @Bean
    public SimpleMessageListenerContainerFactory messageListenerContainerFactory() {
        final SimpleMessageListenerContainerFactory msgContainerFactory = new SimpleMessageListenerContainerFactory();
        msgContainerFactory.setAmazonSqs(amazonSqsClient());
        msgContainerFactory.setMaxNumberOfMessages(1);

        return msgContainerFactory;
    }

    @Bean
    public QueueMessageHandler messageHandler() {
        final QueueMessageHandlerFactory queueMsgHandlerFactory = new QueueMessageHandlerFactory();
        queueMsgHandlerFactory.setAmazonSqs(amazonSqsClient());
        return queueMsgHandlerFactory.createQueueMessageHandler();
    }
}
