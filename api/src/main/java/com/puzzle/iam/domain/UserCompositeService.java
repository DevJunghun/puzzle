package com.puzzle.iam.domain;

import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.api.util.MailSender;
import com.puzzle.api.util.RandomPassword;
import com.puzzle.iam.controller.dto.SignInDto;
import com.puzzle.iam.domain.exceptions.AlreadyExistUserException;
import com.puzzle.iam.domain.exceptions.NotValidPasswordException;
import jakarta.transaction.Transactional;
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
    private final UserTrigger trigger;

    private static final String PWD_REGEX = "(\\d+[A-z]+)|([A-z]+\\d+)";

    @Transactional
    public SignInDto.Create.Response signIn(final SignInDto.Create.Request request) {
        validWhenCreate(request);

        final var userUuid = createUser(request);

        return new SignInDto.Create.Response(userUuid);
    }

    @Transactional
    public void findUsername(final String email) {
        final var user = service.findByEmail(email,BooleanDelete.FALSE, BooleanValidate.TRUE);

        mailSender.send(user.getUsername(), "message");
    }

    @Transactional
    public void findPassword(final String email) {
        final var user = service.findByEmail(email, BooleanDelete.FALSE, BooleanValidate.TRUE);
        final var newPassword = changePassword(user);

        mailSender.send(user.getUsername(), "new password is: " + newPassword);
    }

    @Transactional
    public User findByUsername(final String username, final BooleanDelete delete, final BooleanValidate validate) {
        return service.findByUsername(username, delete, validate);
    }

    @Transactional
    public void delete(final String uuid) {
        final var user = service.findByUuid(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);

        user.setDeleted(true);
        user.setUpdatedAt(LocalDateTime.now());

        service.save(user);
    }

    @Transactional
    public User findByUuid(final String uuid) {
        return service.findByUuid(uuid, BooleanDelete.FALSE, BooleanValidate.TRUE);
    }

    @Transactional
    public void lock(final User user) {
        user.setLocked(true);
        service.save(user);
    }

    private String changePassword(final User user) {
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

    private void validWhenCreate(final SignInDto.Create.Request request) {
        validExistUser(request.getUsername());
        validPwdRegex(request.getPwd());
        checkDuplicateMainEmail(request.getEmail());
    }

    private void checkDuplicateMainEmail(final String email) {
        final var user = service.findByEmail(email, BooleanDelete.FALSE, BooleanValidate.FALSE);

        if (user != null) {
            throw new AlreadyExistUserException(email);
        }
    }

    private void validExistUser(final String username) {
        final var user = service.findByUsername(username, BooleanDelete.FALSE, BooleanValidate.FALSE);

        if (user != null) {
            throw new AlreadyExistUserException(username);
        }
    }

    private String createUser(final SignInDto.Create.Request request) {
        final var user = new User();

        assignUserData(user, request);
        trigger.beforeCreateUser(user);

        return service.create(user);
    }

    private void assignUserData(final User user, final SignInDto.Create.Request request) {
        final var encodedPwd = new BCryptPasswordEncoder().encode(request.getPwd());

        user.setUsername(request.getUsername());
        user.setPassword(encodedPwd);
        user.setEmail(request.getEmail());
        user.setDeleted(false);
    }
}
