package com.jzw.template.DAO.repository;

import com.jzw.template.DAO.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDbRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
}
