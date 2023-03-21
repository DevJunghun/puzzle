package com.puzzle.iam.domain;

import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.api.util.MailSender;
import com.puzzle.api.util.RandomPassword;
import com.puzzle.iam.controller.dto.SignInDto;
import com.puzzle.iam.domain.exceptions.NotValidPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserCompositeService {
    private final UserService service;
    private final MailSender mailSender;
    private final RandomPassword randomPassword;

    private static final String PWD_REGEX = "(\\d+\\w+)|(\\w+\\d)";

    public SignInDto.Create.Response signIn(final SignInDto.Create.Request request) {
        validPwdRegex(request.getPwd());

        final var userUuid = createUser(request);

        return new SignInDto.Create.Response(userUuid);
    }

    public void findUsername(final String email) {
        final var user = service.findByEmail(email,BooleanDelete.FALSE, BooleanValidate.TRUE);

        mailSender.send(user.getUsername(), "message");
    }

    public void findPassword(final String email) {
        final var user = service.findByEmail(email, BooleanDelete.FALSE, BooleanValidate.TRUE);
        final var newPassword = changePassword(user);

        mailSender.send(user.getUsername(), "new password is: " + newPassword);
    }

    public void delete(final String uuid) {
        final var user = service.findByUuid(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);

        user.setDeleted(true);
        user.setUpdatedAt(LocalDateTime.now());

        service.save(user);
    }

    private String changePassword(final Users user) {
        final var newPassword = randomPassword.pwd();

        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        service.create(user);

        return newPassword;
    }

    private void validPwdRegex(final String pwd) {
        if (pwd.matches(PWD_REGEX)) {
            //do nothing

        } else {
            throw new NotValidPasswordException();
        }
    }

    private String createUser(final SignInDto.Create.Request request) {
        final var user = new Users();

        assignUserData(user, request);

        return service.create(user);
    }

    private void assignUserData(final Users user, final SignInDto.Create.Request request) {
        final var encodedPwd = new BCryptPasswordEncoder().encode(request.getPwd());

        user.setUsername(request.getUsername());
        user.setPassword(encodedPwd);
        user.setDeleted(false);
    }
}
