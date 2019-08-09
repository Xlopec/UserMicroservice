package app.service;

import domain.user.Email;
import domain.user.User;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.NonNull;

public interface UserService {
    // todo: return dto instead of domain object
    @NonNull Single<User> register(@NonNull UserForm form);

    @NonNull Maybe<User> findBy(@NonNull Email email);

}
