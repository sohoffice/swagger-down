package sohoffice.swaggerdown.preparer

import io.swagger.v3.oas.models.OpenAPI

/**
 * Simply add entire api to result
 */
class SimplePreparer: Preparer {
  override fun process(api: OpenAPI): Any {
    return api
  }
}
