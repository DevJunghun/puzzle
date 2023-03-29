package com.puzzle.email.domain;

import com.puzzle.api.util.BooleanDelete;
import com.puzzle.api.util.BooleanValidate;
import com.puzzle.email.domain.exception.UserEmailAlreadyExistException;
import com.puzzle.email.domain.exception.UserEmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEmailService {
    private final UserEmailRepository repository;

    public boolean findEmailExists(final String email,final BooleanDelete delete) {
        UserEmail userEmail;

        if(delete.isTrue()) {
            userEmail = repository.findByEmailAndDeletedFalse(email);

        } else{
            userEmail = repository.findByEmail(email);
        }

        return userEmail != null;
    }

    private void validUserNotNull(final UserEmail userEmail, final BooleanValidate validate, final String email) {
        if (userEmail == null && validate.isTrue()) {
            throw new UserEmailNotFoundException(email);
        }
    }
}
