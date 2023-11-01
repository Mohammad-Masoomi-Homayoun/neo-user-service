package com.neo.neouserservice.user.persistance.mapper;


import com.neo.neouserservice.user.domain.model.User;
import com.neo.neouserservice.user.persistance.entity.UserJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserJpaMapper {

    public abstract UserJpa toJpa(User user);

    @Mapping(target = "id", ignore = true)
    public abstract User toDomain(UserJpa userJpa);

}
