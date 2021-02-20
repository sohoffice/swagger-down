package sohoffice.swaggerdown.preparer

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.PathItem
import sohoffice.swaggerdown.data.OperationEntry

class ApiFlattenPreparer : Preparer {
  private val extractors = listOf(
      "GET" to PathItem::getGet,
      "POST" to PathItem::getPost,
      "DELETE" to PathItem::getDelete,
      "PUT" to PathItem::getPut,
      "HEAD" to PathItem::getHead,
      "OPTIONS" to PathItem::getOptions,
      "PATCH" to PathItem::getPatch,
      "TRACE" to PathItem::getTrace
  )

  override fun process(api: OpenAPI): List<OperationEntry> {
    return api.paths.entries.asSequence()
        .flatMap(this::handlePathItem)
        .toList()
  }

  private fun handlePathItem(ent: Map.Entry<String, PathItem>): Sequence<OperationEntry> {
    val path = ent.key
    val item = ent.value
    val all: List<OperationEntry> = extractors.map {
      val x = it.second.invoke(item)
      if (x == null) {
        null
      } else {
        OperationEntry(path, it.first, x)
      }
    }.filterNotNull()
    return all.asSequence()
  }
}
