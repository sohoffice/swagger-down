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

### Response: 200 application/json

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $.str | string |  |
| $.arr[] | string | string 1 description |

### Response: 400 application/json

##### Schema

| Key | Type | Description |
| --- | ---- | ----------- |
| $ | string | string 1 description |
