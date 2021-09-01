package com.jzw.template.DAO.repository;

import com.jzw.template.DAO.entity.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserMongoRepository extends MongoRepository<UserMongo, Long> {
    Optional<UserMongo> findById(Long id);
    UserMongo insert(UserMongo userMongo);

    Optional<UserMongo> findByName(String name);
}
