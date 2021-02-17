package sohoffice.swaggerdown.preparer;

import io.swagger.v3.oas.models.OpenAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * A simple utility to coerce the input string into a single line string.
 *
 * It was implemented so the return value is {@link Function}, which will then be picked up by mustache.
 */
public class NewlineToBrPreparer implements Preparer {
  @Nullable
  @Override
  public Function<String, String> process(@NotNull OpenAPI api) {
    return s -> {
      if (s == null) {
        return null;
      }
      return s.trim()
              .replace("\n", "<br>");
    };
  }
}
