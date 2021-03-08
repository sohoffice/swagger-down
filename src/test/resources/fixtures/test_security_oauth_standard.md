# API Documentation

Security title

#### Info

| Name | Value |
| ---- | ----- |
| Version | 1.0 |


#### Servers

| Description | URL |
| ----------- | --- |
| Foo server | http://foo |


GET /oauth/implicit
-------------------

oauth implicit summary

### Description

oauth implicit description

### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://example.com/api/oauth/dialog | write:pets | write pets |
|  |  |  | read:pets | read pets |

### Response: 200 application/json

Successful response

Empty schema

##### Example

```
some thing
```


GET /oauth/authorizationCode
----------------------------

oauth authorization code summary

### Description

oauth authorization code description

### Security

- OAuth2 Authorization Code

| Type | Flow | Authorization URL | Token URL | Scope | Description |
| ---- | ---- | ----------------- | --------- | ----- | ----------- |
| OAUTH2 | Authorization Code | https://example.com/api/oauth/dialog | https://example.com/api/oauth/token | write:pets | write pets |
|  |  |  |  | read:pets | read pets |

### Response: 200 application/json

Successful response

Empty schema

##### Example

```
got it
```


GET /oauth/authorizationCodeImplicit
------------------------------------

oauth authorization code implicit summary

### Description

oauth authorization code implicit description

### Security

- OAuth2 Implicit

| Type | Flow | Authorization URL | Scope | Description |
| ---- | ---- | ----------------- | ----- | ----------- |
| OAUTH2 | Implicit | https://example.com/api/oauth/dialog | write:pets | write pets |
|  |  |  | read:pets | read pets |

- OAuth2 Authorization Code

| Type | Flow | Authorization URL | Token URL | Scope | Description |
| ---- | ---- | ----------------- | --------- | ----- | ----------- |
| OAUTH2 | Authorization Code | https://example.com/api/oauth/dialog | https://example.com/api/oauth/token | write:pets | write pets |
|  |  |  |  | read:pets | read pets |

### Response: 200 application/json

Successful response

Empty schema

##### Example

```
got it
```
