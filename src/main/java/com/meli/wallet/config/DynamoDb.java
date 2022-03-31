package com.meli.wallet.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

@Configuration
public class DynamoDb {

    @Bean
    public AmazonDynamoDB amazonDynamoDB(@Value("${amazon.dynamo.endpoint:}") String endpoint,
                                         @Value("${amazon.dynamo.region:sa-east-1}") String region,
                                         @Value("${amazon.dynamo.accessKey}") String accessKey,
                                         @Value("${amazon.dynamo.secretKey}") String secretKey) {

        var dynamoClientBuilder = AmazonDynamoDBClient.builder();
        dynamoClientBuilder
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)));

        if (endpoint.isBlank()) {
            dynamoClientBuilder.withRegion(Regions.fromName(region));
        } else {
            dynamoClientBuilder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region));
        }
        return dynamoClientBuilder.build();

    }

    @Bean
    public DynamoDBMapperConfig operationConfigMapper(@Value("${amazon.dynamo.table.wallet}") String walletTable) {
        return dynamoDBMapperConfig(walletTable);
    }

    @Bean
    public DynamoDBMapper walletMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig walletMapper) {
        return new DynamoDBMapper(amazonDynamoDB, walletMapper);
    }

    public DynamoDBMapperConfig dynamoDBMapperConfig(String tableName) {
        return DynamoDBMapperConfig.builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName))
                .build();
    }

    public static class LocalDateTimeConverter implements DynamoDBTypeConverter<String, ZonedDateTime> {

        @Override
        public String convert(final ZonedDateTime time) {
            return time.toString();
        }

        @Override
        public ZonedDateTime unconvert(final String stringValue) {
            try {
                return ZonedDateTime.parse(stringValue);
            } catch (DateTimeParseException exception) {
                return LocalDateTime.parse(stringValue).atZone(ZoneOffset.UTC);
            }
        }
    }
}
