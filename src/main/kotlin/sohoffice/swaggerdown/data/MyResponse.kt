package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.examples.Example
import io.swagger.v3.oas.models.responses.ApiResponse
import sohoffice.swaggerdown.preparer.flattenContent

data class MyResponse(
    val status: String,
    val contentType: String?,
    val schema: MySchema?,
    val description: String? = null,
    val examples: Map<String?, Example?>? = null,
    override val example: Any? = null
) : ExampleExportable {
  constructor(status: String, description: String?, c: MyContent) : this(status, c.contentType, c.schema, description, c.examples, c.example)

  companion object {
    fun fromApiResponse(status: String, r: ApiResponse): List<MyResponse> {
      val contents = flattenContent(r.content)
      return if (contents == null) {
        listOf(MyResponse(status, null, null, r.description))
      } else {
        contents.map {
          MyResponse(status, r.description,  it)
        }
      }
    }
  }
}
