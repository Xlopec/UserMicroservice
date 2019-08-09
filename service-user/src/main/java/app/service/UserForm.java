package app.service;

import javax.annotation.Nullable;

import domain.user.Avatar;
import domain.user.Email;
import domain.user.Nickname;
import lombok.NonNull;
import lombok.Value;

@Value
public class UserForm {
    @NonNull Email email;
    @NonNull Nickname nickname;
    @Nullable Avatar avatar;
}