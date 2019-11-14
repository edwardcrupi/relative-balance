# RelativeBalancer (Requires Java 11)

## Build
In the console open the top level directory and run this command with a java SDK of 11 or higher
```shell
javac -cp . RelativeBalanceTest.java RelativeBalanceRelease.java
```

## Run Tests 

```shell
java RelativeBalanceTest
```

## Run Release Example

```shell
java RelativeBalanceRelease data/test.csv;
```

Then use the following inputs as parameters:

- accountId:
  + ACC334455
- from:
  + 20/10/2018 12:00:00
- to:
  + 20/10/2018 17:00:00

This lightweight ETL programming will quickly calculate the relative balance any csv of the form 

```csv
transactionId, fromAccountId, toAccountId, createdAt, amount, transactionType, relatedTransaction
```
when corresponding parameters are given
