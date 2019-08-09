package app.repository;

import org.springframework.stereotype.Repository;

import app.service.UserForm;
import domain.user.Email;
import domain.user.User;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.NonNull;
import lombok.val;

@Repository
class UserRepositoryImpl implements UserRepository {

    private final @NonNull UserJpaRepository jpaDelegate;

    public UserRepositoryImpl(UserJpaRepository jpaDelegate) {
        this.jpaDelegate = jpaDelegate;
    }

    @Override
    public @NonNull Single<User> create(@NonNull UserForm form) {
        return Single.defer(() -> Single.<User>create(emitter -> emitter.onSuccess(jpaDelegate.saveAndFlush(new UserPo(form)).toEntity())))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public @NonNull Maybe<User> findBy(@NonNull Email email) {
        return Maybe.defer(() -> Maybe.create(emitter -> {
            val option = jpaDelegate.findById(email.getValue()).map(UserPo::toEntity);

            if (option.isPresent()) {
                emitter.onSuccess(option.get());
                return;
            }

            emitter.onComplete();
        }));
    }

}
