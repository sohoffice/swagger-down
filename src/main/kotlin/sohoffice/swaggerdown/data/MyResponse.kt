package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.responses.ApiResponse
import sohoffice.swaggerdown.preparer.flattenContent

data class MyResponse(
    val status: String,
    val contentType: String,
    val schema: MySchema,
    val examples: Map<String?, Example?>? = null,
    override val example: Any? = null
) : ExampleExportable {
  constructor(status: String, c: MyContent) : this(status, c.contentType, c.schema, c.examples, c.example)

  companion object {
    fun fromApiResponse(status: String, r: ApiResponse): List<MyResponse> {
      val contents = flattenContent(r.content)
      return contents?.map {
        MyResponse(status, it)
      } ?: emptyList()
    }
  }
}
