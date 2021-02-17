package sohoffice.swaggerdown.preparer

import io.swagger.v3.oas.models.OpenAPI

interface Preparer {
  fun process(api: OpenAPI): Any?
}
