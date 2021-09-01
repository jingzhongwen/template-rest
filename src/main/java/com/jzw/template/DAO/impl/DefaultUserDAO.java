package com.jzw.template.DAO.impl;

import com.jzw.template.api.dto.User;
import com.jzw.template.DAO.repository.UserMongoRepository;
import com.jzw.template.DAO.UserDAO;
import com.jzw.template.DAO.entity.UserEntity;
import com.jzw.template.DAO.repository.UserDbRepository;
import com.jzw.template.DAO.entity.UserMongo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultUserDAO implements UserDAO {
    @Autowired
    UserDbRepository userDbRepository;
    @Autowired
    UserMongoRepository userMongoRepository;

    @Override
    public Optional<User> findById(Long id){
        Optional<UserEntity> userEntity = userDbRepository.findById(id);
        if(userEntity.isPresent()) {
            User user = new User();
            BeanUtils.copyProperties(userEntity.get(), user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByIdUseMongo(Long id){
        Optional<UserMongo> userMongo = userMongoRepository.findById(id);
        if(userMongo.isPresent()) {
            User user = new User();
            BeanUtils.copyProperties(userMongo.get(), user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userDbRepository.save(userEntity);
    }

    @Override
    public void saveInMongo(User user){
        UserMongo userMongo = new UserMongo();
        BeanUtils.copyProperties(user, userMongo);
        userMongoRepository.save(userMongo);
    }


    @Override
    public Optional<User> findByName(String name) {
        User user = new User();
        Optional<UserMongo> userMongo = userMongoRepository.findByName(name);
        if(userMongo.isPresent()) {
            BeanUtils.copyProperties(userMongo.get(), user);
        }
        return Optional.ofNullable(user);
    }
}
