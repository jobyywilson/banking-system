# banking-system

![alt text](architecture.png "Architecture Diagram")


## To create a account

### Request

`POST http://localhost:8080/account/create`
'

	{
		
	"username":"johnsmith",
	"firstName":"dsds",
	"email":"HEllo@Worlf",
	"lastName":"John",
	"phoneNumber":"098923479237",
	"password":"johnsmith"
	
	
	}
'
## To initate a transaction

### Request
`POST http://localhost:8080/account/startTransaction`



	{
		"transactionType":"CREDIT",
		"amount":20,
		"username":"johnsmith"
	}




transactionType can be either CREDIT or DEBIT
