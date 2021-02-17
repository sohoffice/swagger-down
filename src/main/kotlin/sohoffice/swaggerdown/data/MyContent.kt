package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.examples.Example

data class MyContent(
    val contentType: String,
    val schema: MySchema,
    val examples: Map<String?, Example?>? = null,
    val example: Any? = null
)
