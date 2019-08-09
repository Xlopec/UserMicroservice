package domain.user;

import lombok.*;

import javax.annotation.Nullable;

@Getter
@ToString
@EqualsAndHashCode
public final class User {
    // email serves as identifier for users
    private @NonNull final Email email;
    private @NonNull Nickname nickname;
    private @Nullable Avatar avatar;
    // * implement builder pattern if more fields are needed (esp. nullable)
    public User(@NonNull Email email, @NonNull Nickname nickname, @Nullable Avatar avatar) {
        this.email = email;
        this.nickname = nickname;
        this.avatar = avatar;
    }

    public User(@NonNull Email email, @NonNull Nickname nickname) {
        this(email, nickname, null);
    }

    public void updateNickname(@NonNull Nickname nickname) {
        this.nickname = nickname;
    }

    public void updateAvatar(@NonNull Avatar avatar) {
        this.avatar = avatar;
    }

}