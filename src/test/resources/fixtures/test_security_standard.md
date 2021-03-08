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


GET /global
-----------

Use global auth

### Description

Use global auth description

### Security

##### Option 1

- Http Basic

| Type | Scheme | Description |
| ---- | ------ | ----------- |
| http | basic | Http basic auth |

- Api Key: Header Foo-Api-Key

| Type | Name | In  | Description |
| ---- | ---- | --- | ----------- |
| apiKey | Foo-Api-Key | header | Foo api key auth |

##### Option 2

- Http JWT Bearer

| Type | Scheme | Bearer Format | Description |
| ---- | ------ | ------------- | ----------- |
| http | bearer | JWT | JWT bearer token |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| Header-1 | header | Header 1 description |

### Response: 200 application/json

Successful response

Empty schema

##### Example

```
some thing
```


GET /api
--------

Use api auth only

### Description

Use api auth description

### Security

- Api Key: Header Foo-Api-Key

| Type | Name | In  | Description |
| ---- | ---- | --- | ----------- |
| apiKey | Foo-Api-Key | header | Foo api key auth |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| Header-1 | header | Header 1 description |

### Response: 200 application/json

Successful response

Empty schema

##### Example

```
got it
```
