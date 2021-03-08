package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.servers.Server

data class MyServers(val servers: List<Server>?) {
  fun isHasAny(): Boolean {
    return servers?.isNotEmpty() ?: false
  }
}
