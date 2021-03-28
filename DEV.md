Architecture
============

swagger-down relies on swagger parser to parse the input file. The current version in use is `io.swagger.parser.v3:swagger-parser:2.0.24`.
After parsing, it will decode the parsed structure into a set of MyObjects. See those objects in package `sohoffice.swaggerdown.data`.

These MyObjects exists so that they can be easily handled by the template engine. The template engine is mustache for Java, provided by
`com.github.spullara.mustache.java:compiler:0.9.7`.

Testing
=======

Due to the nature that the utility relies on swagger parser. Conventional mock and test strategy doesn't really work, after all, the result
heavily relies on the parsing output of the spec document. As a result, a set of input YAML spec and expected MD output files were added
as test cases. They can be found in `src/test/resources/fixtures`.

For example, you can find a `test_single_api.yml` file in the fixtures directory. The expected result would be named as
`test_single_api_standard.md`. The `_standard` part exists to represents different flavor of the output document. Although standard is the
only flavor supported for the moment.

Building
========

To build a *fat jar* locally:

```
gradle shadowJar
# The output is found in build/libs/swagger-down-all.jar
```

To build a release version:

1. Git tag the commit to be released with "v + version number", for example `v0.7`
2. Push the tag to github
3. Github action will build automatically and publish it as a draft release
4. Go to the [release page](https://github.com/sohoffice/swagger-down/releases) and publish the draft release.
