package sohoffice.swaggerdown

import com.github.mustachejava.DefaultMustacheFactory
import java.io.Writer

class MyMustacheFactory: DefaultMustacheFactory() {
  override fun encode(value: String?, writer: Writer?) {
    if (writer == null || value == null) {
      return
    }
    writer.write(value)
  }
}
