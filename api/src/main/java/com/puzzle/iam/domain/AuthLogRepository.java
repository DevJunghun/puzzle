package com.puzzle.iam.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthLogRepository extends JpaRepository<AuthLog, String> {
}
