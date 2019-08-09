package app.repository;

import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import app.service.UserForm;
import domain.user.Avatar;
import domain.user.Email;
import domain.user.Nickname;
import domain.user.User;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.val;

@Entity(name = "User")
@Table(name = "User")
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
final class UserPo {
    // of course we can use type adapters on fields but I don't have time for this
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    String email;
    @Column(nullable = false)
    String nickname;
    @Column
    String avatar;

    public UserPo() {}

    public UserPo(@NonNull User user) {
        this.email = user.getEmail().getValue();
        this.nickname = user.getNickname().getValue();

        val avatar = user.getAvatar();

        if (avatar != null) {
            this.avatar = avatar.getUrl().toExternalForm();
        }
    }

    public UserPo(@NonNull UserForm form) {
        this.email = form.getEmail().getValue();
        this.nickname = form.getNickname().getValue();

        val avatar = form.getAvatar();

        if (avatar != null) {
            this.avatar = avatar.getUrl().toExternalForm();
        }
    }

    @SneakyThrows
    public @NonNull User toEntity() {
        val avatar = this.avatar == null ? null : new Avatar(new URL(this.avatar));

        return new User(new Email(email), new Nickname(nickname), avatar);
    }

}
