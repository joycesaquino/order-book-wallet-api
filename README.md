# Wallet API

This API implements a service that will be responsible to transactions considering CREDIT and DEBIT in wallet API


How to test ?

    - docker-compose up
    - Run WalletApplication

API Documentation

    http://localhost:8082/docs

Request Body

    {
        "operations": [
            {
                "value": 1000.00,
                "quantity": 2,
                "userId": 223393,
                "traceId": "cd8e90d4-32b0-40f7-a3a1-8be706474913",
                "operationType": "SALE"
            },
            {
                "value": 1000.00,
                "quantity": 2,
                "userId": 223394,
                "traceId": "cd8e90d4-32b0-40f7-a3a1-8be706474913",
                "operationType": "BUY"
            }
        ]
    }

Curl

        curl --location --request POST 'localhost:8082/wallet' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "operations": [
            {
                "value": 1000.00,
                "quantity": 2,
                "userId": 223393,
                "traceId": "cd8e90d4-32b0-40f7-a3a1-8be706474913",
                "operationType": "SALE"
            },
            {
                "value": 1000.00,
                "quantity": 2,
                "userId": 223394,
                "traceId": "cd8e90d4-32b0-40f7-a3a1-8be706474913",
                "operationType": "BUY"
            }
        ]}'

You can also send message to SQS as arquitecture purpose, the queue was created by docker-compose.
    
        aws sqs send-message \
            --queue-url http://localhost:4566/queue/order-book-wallet-integration-queue \
            --endpoint-url http://localhost:4566 \
            --message-body '{
        "operations": [
            {
                "value": 1000.00,
                "quantity": 2,
                "userId": 223393,
                "traceId": "cd8e90d4-32b0-40f7-a3a1-8be706474913",
                "operationType": "SALE"
            },
            {
                "value": 1000.00,
                "quantity": 2,
                "userId": 223394,
                "traceId": "cd8e90d4-32b0-40f7-a3a1-8be706474913",
                "operationType": "BUY"
            }
        ]
            }' --message-attributes 'contentType={DataType=String, StringValue=application/json;charset=UTF-8}'


Expected response

    HTTP create 201

Validate in local database

    aws dynamodb scan --table-name wallet --endpoint-url http://localhost:4566

