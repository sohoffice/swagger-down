# API Documentation

2 paths title

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

- Http Basic
- Api Key: Header Foo-Api-Key

    | Type | Scheme | Name | In  | Description |
    | ---- | ------ | ---- | --- | ----------- |
    | http | basic | | | Http basic auth |
    | http | apiKey | Foo-Api-Key | Header | Foo api key auth |

- Http JWT Bearer

    | Type | Scheme | Bearer Format | Description |
    | ---- | ------ | ------------- | ----------- |
    | http | bearer | JWT | JWT bearer token |

- Oauth2 implicit

    | Type | Flow | Authorization URL | Scope | Description |
    | ---- | ---- | ----------------- | ----- | ----------- |
    | oauth2 | implicit | https://example.com/api/oauth/dialog | write:pets | write foo |
    | | | | read:pets | read foo |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| Header-1 | header | Header 1 description |

### Response: 200 application/json

Empty schema

POST /api
---------

Use api auth

### Description

Use api auth description

### Security

- Api Key: Header Foo-Api-Key

    | Type | Scheme | Name | In  | Description |
    | ---- | ------ | ---- | --- | ----------- |
    | http | apiKey | Foo-Api-Key | Header | Foo api key auth |

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| Header-1 | header | Header 1 description |

### Response: 204 application/json
