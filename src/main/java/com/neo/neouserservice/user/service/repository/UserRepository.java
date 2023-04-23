package com.neo.neouserservice.user.service.repository;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, ID> {


}
