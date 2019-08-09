package app.service;

import org.springframework.stereotype.Service;

import app.repository.UserRepository;
import domain.user.Email;
import domain.user.User;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.NonNull;

@Service
final class UserServiceImpl implements UserService {

    private final @NonNull UserRepository userRepository;

    public UserServiceImpl(@NonNull UserRepository userRepository /*Add event bus integration or whatever*/) {
        this.userRepository = userRepository;
    }

    @Override
    public @NonNull Single<User> register(@NonNull UserForm form) {
        return userRepository.create(form);
    }

    @Override
    public @NonNull Maybe<User> findBy(@NonNull Email email) {
        return userRepository.findBy(email);
    }

}
