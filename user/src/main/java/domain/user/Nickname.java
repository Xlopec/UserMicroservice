package domain.user;

import javax.annotation.Nullable;

import domain.utils.Preconditions;
import lombok.NonNull;
import lombok.Value;

@Value
public class Nickname {

    public static boolean isValid(@Nullable String value) {
        return value != null && value.length() >= 3;
    }

    @NonNull String value;

    public Nickname(@NonNull String value) {
        Preconditions.require(isValid(value), "Invalid nickname, was %s, should contain at least 3 characters", value);
        this.value = value;
    }
}
