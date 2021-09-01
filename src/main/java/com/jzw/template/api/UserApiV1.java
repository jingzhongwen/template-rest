package com.jzw.template.api;

import com.jzw.template.agent.UserFeignAgent;
import com.jzw.template.api.dto.User;
import com.jzw.template.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(Consts.API_V1_ROOT_PATH + "user")
@RestController
public class UserApiV1 {

    @Autowired
    UserFeignAgent userFeignAgent;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userDAO.findById(id);
        if(!user.isPresent()) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user.get());
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<User> getUserByName(User usera) {
        Optional<User> user = userDAO.findByName(usera.getName());
        if(!user.isPresent()) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user.get());
    }

    @RequestMapping(path = "feign", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserByFeign() {
        return userFeignAgent.getUserById(1L);
    }
}
