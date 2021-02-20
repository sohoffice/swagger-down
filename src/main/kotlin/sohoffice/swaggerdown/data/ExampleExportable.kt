package sohoffice.swaggerdown.data

import com.fasterxml.jackson.databind.JsonNode
import mapper

interface ExampleExportable {
  val example: Any?

  fun hasExample(): Boolean {
    return example != null
  }

  fun isHasJsonExample(): Boolean {
    return example != null && example is JsonNode
  }

  fun getJsonExample(): String {
    return mapper.writerWithDefaultPrettyPrinter()
        .writeValueAsString(example)
  }

}
