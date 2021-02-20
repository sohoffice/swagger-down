package sohoffice.swaggerdown.preparer;

import io.swagger.v3.oas.models.OpenAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.Function;

/**
 * The preparer checks whether a collection is empty or not.
 */
public class CheckEmptyCollectionPreparer implements Preparer {
  @Nullable
  @Override
  public Function<Collection<?>, Boolean> process(@NotNull OpenAPI api) {
    return col -> {
      if (col == null) {
        return false;
      }
      return !col.isEmpty();
    };
  }
}
