package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.parameters.Parameter
import org.apache.commons.lang3.StringUtils
import sohoffice.swaggerdown.SdSpecException
import sohoffice.swaggerdown.preparer.flattenContent

data class OperationEntry(
    val path: String,
    val method: String,
    val op: Operation
) {

  fun isHasParameters(): Boolean {
    return op.parameters != null
  }

  fun getParameters(): List<Parameter> {
    if (op.parameters == null) {
      return emptyList<Parameter>()
    }
    return op.parameters
        .sortedBy { weight(it) }
  }

  fun getRequestBody(): List<MyRequestBody>? {
    val contents = flattenContent(op?.requestBody?.content)

    return contents?.map {
      MyRequestBody(it)
    }
  }

  fun getResponses(): List<MyResponse> {
    return op.responses.flatMap {
      try {
        MyResponse.fromApiResponse(it.key, it.value)
      } catch (e: SdSpecException) {
        throw SdSpecException("$method $path ${it.key}:${e.path}", "Error processing response")
      }
    }
  }

  private fun weight(param: Parameter): Int {
    return when (param.`in`) {
      "path" -> 0
      "header" -> 1
      "query" -> 2
      "cookie" -> 3
      else -> 4
    }
  }
}
