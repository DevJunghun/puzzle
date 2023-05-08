package com.puzzle.iam.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTokenTransactionService {
    private final UserTokenCompositeService compositeService;
    private final UserCompositeService userCompositeService;

    @Transactional
    public User findUser(final String userToken) {
         final var userUuid = compositeService.find(userToken).getUserUuid();

         return userCompositeService.findByUuid(userUuid);
    }
}
