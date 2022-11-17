# BankingAPI

JSON Path + Body

CREATE CUSTOMER ~ POST ~ http://localhost:8080/customers
```
{   
    "firstName": "Isaiah",
    "lastName": "DuPree",
    "addresses":
    [
        {
        "streetNumber": "123",
        "streetName": "Sesame Street",
        "city": "Wilmington",
        "state": "DE",
        "zip": "19809"
        }
    ]   
}
```
UPDATE CUSTOMER ~ PUT ~ http://localhost:8080/customers/1
```
{   
    "id":1,
    "firstName": "Isaiah",
    "lastName": "DuPree",
    "addresses":
    [
        {
        "id": 1,    
        "streetNumber": "2120",
        "streetName": "Sesame Street",
        "city": "Wilmington",
        "state": "DE",
        "zip": "19809"
        }
    ]
}
```

CREATE ACCOUNT ~ POST ~ http://localhost:8080/customers/1/accounts
```
{
    "accountType": "CREDIT",
    "nickname": "string",
    "rewards": 0,
    "balance": 0.0,
    "customerId": 1
}
```

UPDATE ACCOUNT ~ PUT ~ http://localhost:8080/accounts/1
```
{
        "id": 1,
        "accountType": "CREDIT",
        "nickname": "Boss",
        "rewards": 10,
        "balance": 100.0,
        "customerId": 1
        }
```

CREATE BILL ~ POST ~ http://localhost:8080/accounts/1/bills
```
    "billStatus": "PENDING",
    "payee": "Walmart",
    "nickname": "Groceries",
    "creation_date": "2017-01-01",
    "payment_date": "2017-07-25",
    "recurring_date": 25,
    "upcoming_payment_date": "2017-08-25",
    "payment_amount": 27.5,
    "account_id": 1
```

UPDATE BILL ~ PUT ~ http://localhost:8080/bills/1
```
{
    "id": 1,
    "billStatus": "PENDING",
    "payee": "ComEd Receivables",
    "nickname": "Electric bill",
    "creation_date": "2017-01-01",
    "payment_date": "2017-07-25",
    "recurring_date": 25,
    "upcoming_payment_date": "2017-08-25",
    "payment_amount": 40.5,
    "account_id": 1
}
```

CREATE DEPOSIT ~ POST ~ http://localhost:8080/accounts/1/deposits
```
{
   "type": "deposit",
   "transactionDate": "2017-08-21",
   "status": "executed",
   "payeeId": 2,
   "medium": "balance",
   "amount": 25.0,
   "description": "25 bucks saved"
}
```

UPDATE DEPOSIT ~ PUT ~ http://localhost:8080/deposits/1
```
{
        "id": 1,
        "type": "deposit",
        "transactionDate": "2017-08-21",
        "status": "executed",
        "payeeId": 2,
        "medium": "balance",
        "amount": 50.0,
        "description": "50 bucks saved"
}
```

CREATE WITHDRAWAL ~ POST ~ http://localhost:8080/withdrawal/1/withdrawals
```
{
    "type": "Withdrawal",
    "transactionDate": "2020-12-12",
    "status": "executed",
    "payerId": 1,
    "medium": "balance",
    "amount": 20.0,
    "description": "taking out a 20 for a fade"
}
```

UPDATE WITHDRAWAL ~ PUT ~ http://localhost:8080/withdrawals/1
```
{
       "id": 1,
        "type": "Withdrawal",
        "transactionDate": "2020-12-12",
        "status": "executed",
        "payerId": 2,
        "medium": "balance",
        "amount": 10.0,
        "description": "taking out a 10 for a fade"
}
```
