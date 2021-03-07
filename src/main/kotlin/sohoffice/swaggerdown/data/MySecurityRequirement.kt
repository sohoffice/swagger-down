package sohoffice.swaggerdown.data

import io.swagger.v3.oas.models.security.OAuthFlow
import io.swagger.v3.oas.models.security.OAuthFlows
import io.swagger.v3.oas.models.security.SecurityScheme
import sohoffice.swaggerdown.SdSpecException

enum class MySecuritySchemeType {
  HTTP_BASIC,
  JWT_BEARER,
  API_KEY,
  OAUTH,
  OTHER
}

data class MyOauth2SecurityFlow(
  val id: String,
  private val all: OAuthFlows,
  val flow: OAuthFlow
)

interface MySecuritySchemeRenderer {
  val headers: List<String>
  val values: List<List<String>>
}

/**
 * Render simple security scheme. i.e. Not OAuth or openId
 */
class MySecuritySchemeRendererImpl(private val s: MySecuritySchemeImpl) : MySecuritySchemeRenderer {

  private val data = getValuesInternal()

  override val headers = data.map { it.first }
  override val values = listOf(data.map { it.second })

  private fun getValuesInternal(): List<Pair<String, String>> {
    return tableHeaderFunction.mapNotNull { (id, getter) ->
      val v = getter(s)
      if (v != null) {
        id to v.toString()
      } else {
        null
      }
    }
  }

  companion object {

    private val tableHeaderFunction = listOf(
      "Type" to MySecuritySchemeImpl::type,
      "Scheme" to MySecuritySchemeImpl::scheme,
      "Bearer Format" to MySecuritySchemeImpl::bearerFormat,
      "Name" to MySecuritySchemeImpl::name,
      "In " to MySecuritySchemeImpl::`in`,
      "Description" to MySecuritySchemeImpl::description
    )

  }
}

class MySecuritySchemeRendererOauth(private val s: MySecuritySchemeImpl, private val flow: String, private val f: OAuthFlow) : MySecuritySchemeRenderer {

  private val data: List<Pair<String, String>>
    get() {
      return evaluations.mapNotNull { pair ->
        val func = pair.second
        val x = func(this)
        if (x != null) {
          pair.first to x
        } else {
          null
        }
      }
    }

  override val headers: List<String>
    get() {
      return data.map { it.first }
        .plus(listOf("Scope", "Description"))
    }

  override val values: List<List<String>>
    get() = f.scopes?.entries?.mapIndexed { index, it ->
      if (index == 0) {
        data.map { it.second }
          .plus(listOf(it.key, it.value))
      } else {
        data.map { "" }
          .plus(listOf(it.key, it.value))
      }
    } ?: listOf(
      data.map { it.second }
    )

  companion object {

    val evaluations: List<Pair<String, (MySecuritySchemeRendererOauth) -> String?>> = listOf(
      "Type" to { it.s.type?.name ?: "OAUTH2" },
      "Flow" to { it.flow },
      "Authorization URL" to { it.f.authorizationUrl },
      "Token URL" to { it.f.tokenUrl }
    )
  }
}

interface MySecurityScheme {
  fun getSchemeLabel(): String
  val renderer: MySecuritySchemeRenderer
}

data class MySecuritySchemeOauth(
  val orig: MySecuritySchemeImpl,
  val flowId: String,
  val flow: OAuthFlow
) : MySecurityScheme {

  override fun getSchemeLabel(): String {
    return "OAuth2 $flowId"
  }

  override val renderer: MySecuritySchemeRenderer
    get() = MySecuritySchemeRendererOauth(orig, flowId, flow)
}

data class MySecuritySchemeImpl(
  val id: String,
  val _scheme: SecurityScheme,
  val scopes: List<String>? = null
) : MySecurityScheme {

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

  override fun getSchemeLabel(): String {
    return when (schemeType) {
      MySecuritySchemeType.HTTP_BASIC -> "Http Basic"
      MySecuritySchemeType.JWT_BEARER -> "Http JWT Bearer"
      MySecuritySchemeType.API_KEY -> "Api Key: ${`in`?.name?.toLowerCase()?.capitalize()} $name"
      MySecuritySchemeType.OAUTH -> "OAuth2 ${flows?.first()?.id}"
      else -> "Other"
    }
  }

  val schemeType: MySecuritySchemeType = when {
    type == SecurityScheme.Type.HTTP && scheme?.trim()?.toLowerCase() == "basic" ->
      MySecuritySchemeType.HTTP_BASIC
    type == SecurityScheme.Type.HTTP && scheme?.trim()?.toLowerCase() == "bearer" && bearerFormat?.trim()?.toLowerCase() == "jwt" ->
      MySecuritySchemeType.JWT_BEARER
    type == SecurityScheme.Type.APIKEY ->
      MySecuritySchemeType.API_KEY
    type == SecurityScheme.Type.OAUTH2 ->
      MySecuritySchemeType.OAUTH
    else ->
      MySecuritySchemeType.OTHER
  }

  override val renderer = when (schemeType) {
    MySecuritySchemeType.OAUTH -> {
      val flow = flows?.first() ?: throw SdSpecException(this.id, "flows is empty")
      MySecuritySchemeRendererOauth(this, flow.id, flow.flow)
    }
    else -> MySecuritySchemeRendererImpl(this)
  }

  companion object {

    private val securityFlowGetter = listOf(
      "Implicit" to OAuthFlows::getImplicit,
      "Password" to OAuthFlows::getPassword,
      "Client Credentials" to OAuthFlows::getClientCredentials,
      "Authorization Code" to OAuthFlows::getAuthorizationCode
    )

  }
}

data class MySecurityOptions(
  private val schemes: List<MySecuritySchemeImpl>
) {

  fun getFlattenSchemes(): List<MySecurityScheme> {
    return schemes.flatMap {
      if (it.type == SecurityScheme.Type.OAUTH2) {
        it.flows?.map { flow ->
          MySecuritySchemeOauth(it, flow.id, flow.flow)
        } ?: emptyList()
      } else {
        listOf(it)
      }
    }
  }
}

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
        MySecurityOptionsPair("", it.getFlattenSchemes())
      }
      else -> _options.mapIndexed { index, mySecurityOptions ->
        MySecurityOptionsPair("Option ${index + 1}", mySecurityOptions.getFlattenSchemes())
      }
    }
  }
}
