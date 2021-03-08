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


GET /foo
--------

Get foo summary

### Description

Get foo description

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| Header-1 | header | Header 1 description |

### Response: 200 application/json

Successful response

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $.str | string |  |
| $.arr[] | string | string 1 description |

### Response: 400 application/json

bad request

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $ | string | string 1 description |


POST /bar
---------

Post bar summary

### Description

Post bar description

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| Header-1 | header | Header 1 description |

### Request Body: application/json

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $ | string | string 1 description |

### Response: 200 application/json

Successful response

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $.str | string |  |
| $.arr[] | string | string 1 description |

### Response: 400 application/json

bad request

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $ | string | string 1 description |
