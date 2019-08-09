package app.repository;

import domain.user.Email;
import domain.user.User;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.NonNull;
import app.service.UserForm;

public interface UserRepository {

    @NonNull Single<User> create(@NonNull UserForm form);

    @NonNull Maybe<User> findBy(@NonNull Email email);

}
