package com.dng.cs.core.repository.base;

import com.dng.cs.core.entity.RoleEntity;
import com.dng.cs.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleBaseRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findAllByUserId(Integer user);
}
