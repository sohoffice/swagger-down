package sohoffice.swaggerdown.preparer;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * A simple text util to return trim string from the input.
 *
 * This is implementated as java class so mustache can pick it up.
 */
public class TrimPreparer implements Preparer{
  @Nullable
  @Override
  public Function<String, String> process(@NotNull OpenAPI api) {
    return StringUtils::trimToNull;
  }
}
