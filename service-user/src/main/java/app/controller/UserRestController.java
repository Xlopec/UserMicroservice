package app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.service.UserForm;
import app.service.UserService;
import domain.user.Email;
import domain.user.User;
import io.reactivex.Single;
import lombok.NonNull;

@RestController("user")
@RequestMapping(path = "/user")
final class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public @NonNull Single<User> register(@RequestBody @NonNull UserForm form) {
        return userService.register(form);
    }

    @GetMapping(value = "/find")
    public @NonNull Single<ResponseEntity<User>> findBy(@RequestParam(value = "email") @NonNull Email email) {
        return userService.findBy(email)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .switchIfEmpty(Single.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

}
