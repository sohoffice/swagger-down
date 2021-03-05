package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.security.OAuthFlow
import io.swagger.v3.oas.models.security.OAuthFlows
import io.swagger.v3.oas.models.security.SecurityScheme

data class MyOauth2SecurityFlow(
  val id: String,
  private val all: OAuthFlows,
  val flow: OAuthFlow
)

private val securityFlowGetter = listOf(
  "Implicit" to OAuthFlows::getImplicit,
  "Password" to OAuthFlows::getPassword,
  "Client Credentials" to OAuthFlows::getClientCredentials,
  "Authorization Code" to OAuthFlows::getAuthorizationCode
)

data class MySecurityScheme(
  val id: String,
  val _scheme: SecurityScheme,
  val scopes: List<String>? = null
) {

  val type: SecurityScheme.Type? = _scheme.type
  val name: String? = _scheme.name
  val description: String? = _scheme.description
  val `in`: SecurityScheme.In? = _scheme.`in`
  val scheme: String? = _scheme.scheme
  val bearerFormat: String? = _scheme.bearerFormat

  // oauth2
  val flows: List<MyOauth2SecurityFlow>? = if (_scheme.flows != null)
    securityFlowGetter.fold(mutableListOf()) { acc, (id, getter) ->
      val flow = getter(_scheme.flows)
      if (flow != null) {
        acc.add(MyOauth2SecurityFlow(id, _scheme.flows, flow))
      }
      acc
    } else null

  val openIdConnectUrl: String? = _scheme.openIdConnectUrl

  fun getSchemeLabel(): String {
    return when {
      type == SecurityScheme.Type.HTTP && scheme?.trim()?.toLowerCase() == "basic" ->
        "Http Basic"
      type == SecurityScheme.Type.HTTP && scheme?.trim()?.toLowerCase() == "bearer" && bearerFormat?.trim()?.toLowerCase() == "jwt" ->
        "Http JWT Bearer"
      type == SecurityScheme.Type.APIKEY ->
        "Api Key: ${`in`?.name?.toLowerCase()?.capitalize()} $name"
      else -> "Other"
    }
  }

  val values = getValuesInternal()

  private fun getValuesInternal(): List<Pair<String, String>> {
    return tableHeaderFunction.mapNotNull { (id, getter) ->
      val v = getter(this)
      if (v != null) {
        id to v.toString()
      } else {
        null
      }
    }
  }

}

private val tableHeaderFunction = listOf(
  "Type" to MySecurityScheme::type,
  "Scheme" to MySecurityScheme::scheme,
  "Bearer Format" to MySecurityScheme::bearerFormat,
  "Name" to MySecurityScheme::name,
  "In " to MySecurityScheme::`in`,
  "Description" to MySecurityScheme::description
)

data class MySecurityOptions(
  val schemes: List<MySecurityScheme>
)

data class MySecurityOptionsPair(
  val optionHeader: String,
  val optionSchemes: List<MySecurityScheme>
)

data class MySecurityRequirements(
  private val _options: List<MySecurityOptions>
) {

  fun isHavingManyOptions(): Boolean {
    return _options.size > 1
  }

  fun getOptions(): List<MySecurityOptionsPair>? {
    return when (_options.size) {
      0 -> null
      1 -> _options.map {
        MySecurityOptionsPair("", it.schemes)
      }
      else -> _options.mapIndexed { index, mySecurityOptions ->
        MySecurityOptionsPair("Option ${index + 1}", mySecurityOptions.schemes)
      }
    }
  }
}
