# Wallet API

This API implements a service that will be responsible to transactions considering CREDIT and DEBIT in wallet API


How to test ?

    - docker-compose up
    - Run WalletApplication

Request

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

Expected response

    HTTP create 201

Validate in local database

    aws dynamodb scan --table-name wallet --endpoint-url http://localhost:4566

