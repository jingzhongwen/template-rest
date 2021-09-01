package com.jzw.template.agent;

import com.jzw.template.api.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-client", url="http://localhost:8080/api/v1/user")
public interface UserFeignAgent {

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ResponseEntity<User> getUserById(@PathVariable("id") Long id);
}
