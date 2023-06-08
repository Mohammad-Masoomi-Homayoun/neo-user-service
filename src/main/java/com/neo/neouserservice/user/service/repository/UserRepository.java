package com.neo.neouserservice.user.service.repository;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.user.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, ID> {

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);
}
