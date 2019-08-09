package app.config.gson;

import org.springframework.http.HttpStatus;

import lombok.NonNull;
import lombok.Value;

@Value
public class ApiError {
    @NonNull String status;
    int code;
    @NonNull String message;

    public ApiError(@NonNull HttpStatus status, @NonNull Throwable cause) {
        this.status = status.getReasonPhrase();
        this.code = status.value();
        this.message = cause.getLocalizedMessage();
    }

}
