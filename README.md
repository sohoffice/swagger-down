swagger-down is a OpenAPI 3 to markdown / html renderer.

It is a command line application that take the swagger spec file as input then render to stdout.
Typical usage will redirect the output directly to a file for later processing.

Example:

```
java -jar build/libs/swagger-down-all.jar foo.yml > foo.md
```

Output Document Structure
-------------------------

The output document is simplified, and is rendered in the following order:

1. Info
2. Server
3. API flattened into `method path`

Within the API endpoint, the sections are rendered as the below

1. Summary
2. Description
3. Parameters
4. Request body
5. Responses flattened into `status content-type`

The schema is flattened into a table, with key represented in JSON path.

For example:

```
| Key | Type | Description |
| --- | ---- | ----------- |
| $.code | string | Error code |
| $.message | string | Human readable error message |
| $.parameters[].name | string | Parameter name |
| $.parameters[].value | string | Parameter value |
```

Usage
-----

```
java -jar build/libs/swagger-down-all.jar --help
Usage: <main class> [-h] [-f=<format>] [-r=<flavor>] FILE
      FILE                Swagger YAML files to convert
  -f, --format=<format>   The output format
  -h, --help              display a help message
  -r, --flavor=<flavor>   The generation flavor
```
