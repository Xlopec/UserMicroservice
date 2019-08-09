package app.config.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.Collections;

import lombok.NonNull;
import lombok.val;

@Configuration
public class GsonConfig {

    @Bean
    public @NonNull HttpMessageConverters createMessageConverters() {
        return new HttpMessageConverters(true,
                Collections.singletonList(createGsonMessageConverter(EmailAdapter.INSTANCE,
                        NicknameAdapter.INSTANCE, AvatarAdapter.INSTANCE, UserFormAdapter.INSTANCE)));
    }

    @NonNull
    private GsonHttpMessageConverter createGsonMessageConverter(@NonNull TypeAdapter<?>... adapters) {
        val builder = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        for (val a : adapters) {
            builder.registerTypeAdapter(a.isFor(), a);
        }

        val gsonMessageConverter = new GsonHttpMessageConverter();

        gsonMessageConverter.setGson(builder.create());
        return gsonMessageConverter;
    }

}
