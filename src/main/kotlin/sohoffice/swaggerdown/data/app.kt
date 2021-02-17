import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.oas.models.parameters.Parameter

val mapper = ObjectMapper()

fun Parameter.getSingleLineDescription() {
  this.description?.trim()
      ?.replace("\n", "<br>")
}
