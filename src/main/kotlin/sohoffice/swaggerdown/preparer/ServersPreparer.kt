package sohoffice.swaggerdown.preparer

import io.swagger.v3.oas.models.OpenAPI
import sohoffice.swaggerdown.data.MyServers

class ServersPreparer : Preparer {
  override fun process(api: OpenAPI): MyServers {
    val servers = api.servers?.toList()
        ?.filter {
          it.description != null && it.url != null && it.url != "/"
        }
    if (servers == null || servers.isEmpty()) {
      return MyServers(null)
    }
    return MyServers(servers)
  }
}
