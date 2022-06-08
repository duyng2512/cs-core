package com.dng.cs.core.repository.base;

import com.dng.cs.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBaseRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByUserName(String username);
}
