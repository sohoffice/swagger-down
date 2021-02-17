package sohoffice.swaggerdown.data

import com.fasterxml.jackson.databind.JsonNode
import mapper

interface JsonExampleExportable {
  val example: Any?

  fun isHasJsonExample(): Boolean {
    return example!=null && example is JsonNode
  }

  fun getJsonExample(): String {
    return mapper.writerWithDefaultPrettyPrinter()
        .writeValueAsString(example)
  }

}
