swagger-down is a OpenAPI 3 (Swagger 3.0) spec to markdown / html renderer.

It is a command line application that take the swagger spec file as input and render to stdout.
Typical usage will redirect the output directly to a file for later processing.

Example:

```
java -jar swagger-down-all.jar foo.yml > foo.md
```

swagger-down doesn't support swagger 2.0, there's a number of options supporting it.
So we just decided not to re-invent the wheel.

Output Document
---------------

The basic idea around the design of generated document is `flatten`.

Swagger spec promotes re-usability, so the schema can be defined as a component and referenced when necessary.
Swagger UI preserves the common component section, but also show schema directly where it is needed.

A markdown document is less interactive.

If the reader has to jump back and forth to lookup schema definition, it will soon makes reading a very tedious thing to do.
So a swagger-down generated document basically flattens all schema and make them available when they are needed.
For example: if the schema is referenced in request body, the details will be added right in the relevant section.
The same rule applies to responses.

Also, the swagger spec groups different methods under the same path. This makes perfect sense with Swagger UI,
but also make the markdown document more verbose and creates more hierarchy.
As a result, all APIs are flattened into a list, but placed next to each other to make reading more smoothly.
This rules also applies to different request content types or different response status code + content types.

#### JSON schema

JSON is the de-facto format of API in the modern age, but documenting JSON is not as straight forward as it seems.

swagger-down takes the flattening approach and converts the entire schema into a table, with key represented in JSON path.

Take this JSON for example:

```json
{
  "code": "FOO",
  "message": "Foo message",
  "parameters": [
    {
      "name": "param1",
      "value": "value"
    }
  ]
}
```

The schema will be documented in below format.

```
| Key | Type | Description |
| --- | ---- | ----------- |
| $.code | string | Error code |
| $.message | string | Human readable error message |
| $.parameters[].name | string | Parameter name |
| $.parameters[].value | string | Parameter value |
```

We hope the tabular display help to epress the schema element in a clearer way.

#### Document organization

With the above concepts in mind, the output document is organized in the following order:

1. Info
2. Servers
3. API flattened into `method path` (ex: GET /foo/bar)

Within each API endpoint, the sections are rendered as the below

1. Summary
2. Description
3. Parameters
4. Request body
5. Responses flattened into `status content-type` (ex: 200 application/json)

#### Output format

- markdown
- html

The HTML is unstyled and is more suitable when any of the following is true

- You have HTML markup in your spec file. You'll need to style the output yourself.
- You have a way to style your HTML. For example, it can be pasted into Confluence wiki.

Limitations
-----------

- Swagger 3 only
- Advanced swagger feature like oneOf, anyOf are not supported. We're looking to add them if requested.
- Redundant empty lines are inserted into the markdown output.

Pre-requisite
-------------

You need Java 8+ to run.

Running
-------

Download the latest binary from [release](https://github.com/sohoffice/swagger-down/releases) 
and execute in command prompt with:

```
java -jar swagger-down-all.jar
```

The downloaded jar is a `fat jar`, it can be executed without further dependencies.

## Usage

```
java -jar build/libs/swagger-down-all.jar -help
Usage: <main class> [-h] [-f=<format>] [-r=<flavor>] FILE
      FILE                Swagger YAML files to convert
  -f, --format=<format>   The output format. Values: MD, HTML
  -h, --help              display a help message
  -r, --flavor=<flavor>   The generation flavor. Values: STANDARD
```
