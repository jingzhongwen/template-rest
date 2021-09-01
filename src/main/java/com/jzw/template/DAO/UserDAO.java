package com.jzw.template.DAO;

import com.jzw.template.api.dto.User;

import java.util.Optional;

public interface UserDAO {
    abstract Optional<User> findById(Long id);

    Optional<User> findByIdUseMongo(Long id);

    void save(User user);

    void saveInMongo(User user);

    Optional<User> findByName(String name);
}
