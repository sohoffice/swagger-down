package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.media.MediaType

data class MyRequestBody(
    val contentType: String,
    val schema: MySchema,
    val examples: Map<String?, Example?>? = null,
    override val example: Any? = null
): ExampleExportable {
  constructor(contentType: String, m: MediaType): this(contentType, MySchema.fromSchema(m.schema), m.examples, m.example)

  constructor(c: MyContent): this(c.contentType, c.schema, c.examples, c.example)

  fun isHasExample(): Boolean {
    return example != null
  }
}
