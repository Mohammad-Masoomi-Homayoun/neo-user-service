package com.neo.neouserservice.user.persistance.repository;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.user.persistance.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpa, ID> {

    UserJpa findUserByEmail(String email);

    boolean existsByEmail(String email);
}
