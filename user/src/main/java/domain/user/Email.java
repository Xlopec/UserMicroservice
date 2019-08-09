package domain.user;

import domain.utils.Preconditions;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.regex.Pattern;

@Value
public class Email {

    public static final Pattern EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValid(@Nullable String value) {
        return value != null && EMAIL_ADDRESS_REGEX.matcher(value).matches();
    }

    String value;

    public Email(@NonNull String value) {
        Preconditions.require(isValid(value), "Invalid email, was %s", value);
        this.value = value;
    }

}
