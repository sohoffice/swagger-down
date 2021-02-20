package sohoffice.swaggerdown.preparer

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.servers.Server

class ServersPreparer : Preparer {
  override fun process(api: OpenAPI): List<Server>? {
    val servers = api.servers?.toList()
        ?.filter {
          it.description != null && it.url != null && it.url != "/"
        }
    if (servers == null || servers.isEmpty()) {
      return null;
    }
    return servers
  }
}
