package sohoffice.swaggerdown.preparer;

import io.swagger.v3.oas.models.OpenAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Generate a function that repeat the `repeatable` according to the input string length.
 *
 * This can be used to generate h2 markdown like the below
 *
 * ```
 * Foo h2
 * ------
 * ```
 */
public class RepeaterPreparer implements Preparer {
  private final String repeatable;

  public RepeaterPreparer(String repeatable) {
    this.repeatable = repeatable;
  }

  @Nullable
  @Override
  public Function<String, String> process(@NotNull OpenAPI api) {
    return s -> {
      if (s == null) {
        return null;
      }
      return IntStream.range(0, s.length())
              .mapToObj(n -> repeatable)
              .collect(Collectors.joining());
    };
  }
}
