package sohoffice.swaggerdown.preparer

import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.Schema
import sohoffice.swaggerdown.SdSpecException
import sohoffice.swaggerdown.data.MyContent
import sohoffice.swaggerdown.data.MySchema
import sohoffice.swaggerdown.data.MySchemaItem

fun flattenSchemaItems(prefix: String = "$", schema: Schema<*>): List<MySchemaItem> {
  return when (schema.type) {
    "object" -> {
      val p1 = schema.properties?.flatMap {
        val nextPrefix = "${prefix}.${it.key}"
        flattenSchemaItems(nextPrefix, it.value)
      } ?: emptyList()
      val ap = schema?.additionalProperties
      val p2 = if(ap!=null) {
        listOf(when(ap) {
          is Boolean -> MySchemaItem("*", "*")
          is Schema<*> -> MySchemaItem("*", ap.type, ap.description)
          else -> throw SdSpecException(prefix, "$ap is a valid addionalProperties")
        })
      } else {
        emptyList()
      }
      p1 + p2
    }
    "array" -> flattenSchemaItems("$prefix[]", (schema as ArraySchema).items)
    else ->
      listOf(
          MySchemaItem(prefix, schema.type, schema?.description)
      )
  }
}

fun flattenContent(c: Content?): List<MyContent>? {
  return c?.entries?.map {
    val contentType = it.key
    val m = it.value
    val schema = MySchema.fromSchema(m.schema)
    MyContent(
        contentType, schema, m.examples, m.example
    )
  }
}
