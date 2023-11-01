package com.neo.neouserservice.user.persistance.repository;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.user.domain.model.User;
import com.neo.neouserservice.user.persistance.mapper.UserJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    private final UserJpaRepository repository;
    private final UserJpaMapper mapper;

    public UserRepository(UserJpaRepository repository, UserJpaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public User save(User user) {
        return mapper.toDomain(repository.save(mapper.toJpa(user)));
    }

    public User findUserByEmail(String email) {
        return mapper.toDomain(repository.findUserByEmail(email));
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public User findById(ID id) {
        return mapper.toDomain(repository.findById(id).orElse(null));
    }
}
