package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.media.Schema
import sohoffice.swaggerdown.preparer.flattenSchemaItems

data class MySchema(
    val description: String?,
    override val example: Any?,
    val items: List<MySchemaItem>?
): JsonExampleExportable {
  fun isNotEmpty(): Boolean {
    return items != null && items.isNotEmpty()
  }

  companion object {
    fun fromSchema(s: Schema<Any>): MySchema {
      val items = flattenSchemaItems(schema = s)
      val items2 = if (items.size == 1 && items.first().type != "string" && items.first().type != "array") {
        null
      } else {
        items
      }
      return MySchema(s.description, s.example, items2)
    }
  }
}

