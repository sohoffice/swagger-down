# API Documentation

Schema title

Single API description

#### Info

| Name | Value |
| ---- | ----- |
| Version | 1.0 |


#### Servers

| Description | URL |
| ----------- | --- |
| Foo server | http://foo |
| Bar server | http://bar |


GET /foo
--------

Get foo summary

### Description

Foo description

### Parameters

| Name   | In  | Description |
| ------ | --- | ----------- |
| Header-1 | header | Header 1 description |

### Request Body: application/json

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $ | string | string 1 description |

##### Example

```
foo example
```

### Response: 200

Successful response

### Response: 400

bad request

| Error code | Description |
| ---------- | ----------- |
| Foo        | Foo error   |
