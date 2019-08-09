package app.config.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;
import java.net.URL;

import app.service.UserForm;
import domain.user.Avatar;
import domain.user.Email;
import domain.user.Nickname;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

// we need custom adapter for UserForm since Gson in another case may set
// nullable values on non-nullable fields (gson uses reflection) that can violate our validation
// constraints, invalid instance can be constructed
final class UserFormAdapter implements TypeAdapter<UserForm> {

    static final TypeAdapter<UserForm> INSTANCE = new UserFormAdapter();

    private UserFormAdapter() {
    }

    @Override
    public @NonNull Class<? extends UserForm> isFor() {
        return UserForm.class;
    }

    @Override
    public UserForm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        val obj = json.getAsJsonObject();
        return new UserForm(context.deserialize(obj.get("email"), Email.class),
                context.deserialize(obj.get("nickname"), Nickname.class), context.deserialize(obj.get("avatar"), Avatar.class));
    }

    @Override
    public JsonElement serialize(UserForm src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src);
    }
}

final class EmailAdapter implements TypeAdapter<Email> {

    static final TypeAdapter<Email> INSTANCE = new EmailAdapter();

    private EmailAdapter() {
    }

    @Override
    public @NonNull Class<? extends Email> isFor() {
        return Email.class;
    }

    @Override
    public Email deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Email(json.getAsString());
    }

    @Override
    public JsonElement serialize(Email src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}

final class NicknameAdapter implements TypeAdapter<Nickname> {

    static final TypeAdapter<Nickname> INSTANCE = new NicknameAdapter();

    private NicknameAdapter() {
    }

    @Override
    public @NonNull Class<? extends Nickname> isFor() {
        return Nickname.class;
    }

    @Override
    public Nickname deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Nickname(json.getAsString());
    }

    @Override
    public JsonElement serialize(Nickname src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getValue());
    }
}

final class AvatarAdapter implements TypeAdapter<Avatar> {

    static final TypeAdapter<Avatar> INSTANCE = new AvatarAdapter();

    private AvatarAdapter() {
    }

    @Override
    public @NonNull Class<? extends Avatar> isFor() {
        return Avatar.class;
    }

    @Override
    @SneakyThrows
    public Avatar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Avatar(new URL(json.getAsString()));
    }

    @Override
    public JsonElement serialize(Avatar src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getUrl().toExternalForm());
    }
}