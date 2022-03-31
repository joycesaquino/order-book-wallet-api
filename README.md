# Order book API

This API implements a service that will be responsible to transactions considering CREDIT and DEBIT in wallet API


How to test ?

    - docker-compose up
    - Run WalletApplication

Request

    

Expected response

    HTTP create 201

Validate in local database

    aws dynamodb scan --table-name wallet --endpoint-url http://localhost:4566

