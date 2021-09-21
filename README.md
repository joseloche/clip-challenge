Clip Api Challenge
The Api has the purpose of accept Payments Request, process Disbursements.

Technologies
Spring Boot
JPA
H2
Maven
Java 11

Running the Api

1.- Use clip-0.0.1-SNAPSHOT.jar

2.- Execute comand line: java -jar clip-0.0.1-SNAPSHOT.jar

Web API Resources

1.- GET...
http://localhost:8080/api/clip/users/payment
response:
[
    {
        "id": 1,
        "name": "Alfredo",
        "firstName": "Jimenez",
        "lastName": "Herrera",
        "payment": [
            {
                "id": 1,
                "amount": 105.10,
                "status": "NEW",
                "ltransaction": "2021-09-19"
            },
            {
                "id": 2,
                "amount": 105.10,
                "status": "NEW",
                "ltransaction": "2021-09-19"
            }
        ]
    },
    {
        "id": 2,
        "name": "Angelica",
        "firstName": "Delgadillo",
        "lastName": "Campos",
        "payment": [
        ]
    }
]

2.- POST...
http://localhost:8080/api/clip/createPayload

Request:
{
  "userId":1,
  "amount":"1"
}

Response:
Payload Created Successfully.


3.- POST...
http://localhost:8080/api/clip/disbursement
Response:
[
    {
        "amount": 101.60,
        "user": "Alfredo Jimenez",
        "lastTransaccion": "20/09/2021"
    },
    {
        "amount": 101.60,
        "user": "Alfredo Jimenez",
        "lastTransaccion": "20/09/2021"
    }
]

4.- GET...
http://localhost:8080/api/clip/report/1
Response:
{
    "idUsuario": 1,
    "name": "Alfredo",
    "payments_sum": 2,
    "new_payments": 0
}

5.- POST...
http://localhost:8080/authenticate
Request:
{
  "username":"admin",
  "password":"password"
}
Reaponse:
{
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXZhaW51c2UiLCJleHAiOjE2MzIxMjg4ODEsImlhdCI6MTYzMjExMDg4MX0.clZR-3JvycfYe6cZ3EW39N7-oRe4PyhLSPUymoUMyYrK1kYRaHeC0rIduVfdTRl4ZximJf6dPtYlx_1rp7X0UQ"
}

