package domain.misc;

import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * Represents validation result
 */
@Value
public class Validated {

    private static final Validated INSTANCE = new Validated(true, null);

    boolean isValid;
    @Nullable String details;

    @NonNull
    public static Validated ofValid() {
        return INSTANCE;
    }

    @NonNull
    public static Validated ofInvalid(@NonNull String details) {
        return new Validated(false, details);
    }

    @NonNull
    public static Validated of(@Nullable String input, @NonNull Function<String, Boolean> validator, @NonNull String message) {
        return validator.apply(input) ? ofValid() : ofInvalid(message);
    }

    private Validated(boolean isValid, @Nullable String details) {
        this.isValid = isValid;
        this.details = details;
    }

}
