# API Documentation

Swagger Petstore

This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters.

#### Info

| Name | Value |
| ---- | ----- |
| Terms of service | http://swagger.io/terms/ |
| Contact |  apiteam@swagger.io  |
| License | Apache 2.0 http://www.apache.org/licenses/LICENSE-2.0.html |
| Version | 1.0.5 |


POST /pet/{petId}/uploadImage
-----------------------------

uploads an image

### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://petstore.swagger.io/oauth/authorize | read:pets | read your pets |
|  |  |  | write:pets | modify pets in your account |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| petId | path | ID of pet to update |

### Request Body: multipart/form-data

Empty schema

### Response: 200 application/json

successful operation

Empty schema


POST /pet
---------

Add a new pet to the store

### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://petstore.swagger.io/oauth/authorize | read:pets | read your pets |
|  |  |  | write:pets | modify pets in your account |

### Request Body: application/json

Empty schema

### Request Body: application/xml

Empty schema


PUT /pet
--------

Update an existing pet

### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://petstore.swagger.io/oauth/authorize | read:pets | read your pets |
|  |  |  | write:pets | modify pets in your account |

### Request Body: application/json

Empty schema

### Request Body: application/xml

Empty schema


GET /pet/findByStatus
---------------------

Finds Pets by status

### Description

Multiple status values can be provided with comma separated strings
### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://petstore.swagger.io/oauth/authorize | read:pets | read your pets |
|  |  |  | write:pets | modify pets in your account |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| status | query | Status values that need to be considered for filter |

### Response: 200 application/json

successful operation

Empty schema

### Response: 200 application/xml

successful operation

Empty schema


GET /pet/findByTags
-------------------

Finds Pets by tags

### Description

Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://petstore.swagger.io/oauth/authorize | read:pets | read your pets |
|  |  |  | write:pets | modify pets in your account |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| tags | query | Tags to filter by |

### Response: 200 application/json

successful operation

Empty schema

### Response: 200 application/xml

successful operation

Empty schema


GET /pet/{petId}
----------------

Find pet by ID

### Description

Returns a single pet
### Security

- Api Key: Header api_key

| Type | Name | In  |
| ---- | ---- | --- |
| apiKey | api_key | header |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| petId | path | ID of pet to return |

### Response: 200 application/json

successful operation

Empty schema

### Response: 200 application/xml

successful operation

Empty schema


POST /pet/{petId}
-----------------

Updates a pet in the store with form data

### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://petstore.swagger.io/oauth/authorize | read:pets | read your pets |
|  |  |  | write:pets | modify pets in your account |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| petId | path | ID of pet that needs to be updated |

### Request Body: application/x-www-form-urlencoded

Empty schema


DELETE /pet/{petId}
-------------------

Deletes a pet

### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://petstore.swagger.io/oauth/authorize | read:pets | read your pets |
|  |  |  | write:pets | modify pets in your account |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| petId | path | Pet id to delete |
| api_key | header |  |


POST /store/order
-----------------

Place an order for a pet

### Request Body: application/json

Empty schema

### Response: 200 application/json

successful operation

Empty schema

### Response: 200 application/xml

successful operation

Empty schema


GET /store/order/{orderId}
--------------------------

Find purchase order by ID

### Description

For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions
### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| orderId | path | ID of pet that needs to be fetched |

### Response: 200 application/json

successful operation

Empty schema

### Response: 200 application/xml

successful operation

Empty schema


DELETE /store/order/{orderId}
-----------------------------

Delete purchase order by ID

### Description

For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors
### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| orderId | path | ID of the order that needs to be deleted |


GET /store/inventory
--------------------

Returns pet inventories by status

### Description

Returns a map of status codes to quantities
### Security

- Api Key: Header api_key

| Type | Name | In  |
| ---- | ---- | --- |
| apiKey | api_key | header |

### Response: 200 application/json

successful operation

Empty schema


POST /user/createWithArray
--------------------------

Creates list of users with given input array

### Request Body: application/json

Empty schema


POST /user/createWithList
-------------------------

Creates list of users with given input array

### Request Body: application/json

Empty schema


GET /user/{username}
--------------------

Get user by user name

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| username | path | The name that needs to be fetched. Use user1 for testing. |

### Response: 200 application/json

successful operation

Empty schema

### Response: 200 application/xml

successful operation

Empty schema


DELETE /user/{username}
-----------------------

Delete user

### Description

This can only be done by the logged in user.
### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| username | path | The name that needs to be deleted |


PUT /user/{username}
--------------------

Updated user

### Description

This can only be done by the logged in user.
### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| username | path | name that need to be updated |

### Request Body: application/json

Empty schema


GET /user/login
---------------

Logs user into the system

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| username | query | The user name for login |
| password | query | The password for login in clear text |

### Response: 200 application/json

successful operation

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $ | string |  |

### Response: 200 application/xml

successful operation

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $ | string |  |


GET /user/logout
----------------

Logs out current logged in user session


POST /user
----------

Create user

### Description

This can only be done by the logged in user.
### Request Body: application/json

Empty schema
