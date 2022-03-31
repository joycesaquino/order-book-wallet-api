#!/bin/bash

endpointUrl=http://${AWS_HOST}:${AWS_PORT}

function configure(){
    aws configure set aws_access_key_id default_access_key
    aws configure set aws_secret_access_key default_secret_key
    aws configure set region sa-east-1
}

function createTable() {
    printf 'Creating wallet table'
    aws dynamodb --endpoint-url=${endpointUrl} create-table --table-name wallet \
    --attribute-definitions \
        AttributeName=userId,AttributeType=N \
        AttributeName=id,AttributeType=S \
    --key-schema \
        AttributeName=userId,KeyType=HASH \
        AttributeName=id,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5
}


function createQueue() {
    aws --endpoint-url=${endpointUrl} sqs create-queue --queue-name order-book-wallet-integration-queue
}

configure
createTable
createQueue

