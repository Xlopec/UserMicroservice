package app.config.gson;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import lombok.NonNull;

interface TypeAdapter<T> extends JsonSerializer<T>, JsonDeserializer<T> {
    @NonNull Class<? extends T> isFor();
}
