package com.puzzle.iam.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository  extends JpaRepository<UserToken, String> {
    UserToken findByUserToken(final String userToken);
    UserToken findByUserUuid(final String userUuid);
}
