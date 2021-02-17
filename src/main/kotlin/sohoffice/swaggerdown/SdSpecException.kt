package sohoffice.swaggerdown

class SdSpecException(val path: String, msg: String): RuntimeException("$msg: $path")
