package com.eddie.controller;

import com.eddie.model.User;
import com.eddie.response.impl.DataResponse;
import com.eddie.response.impl.GuildSystemExceptionResponse;
import com.eddie.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/users/")
public class UserController {

    private UserService userService;

    private DataResponse<User> userResponse;

    @Autowired
    public UserController(UserService userService, DataResponse<User> userResponse) {
        this.userService = userService;
        this.userResponse = userResponse;

    }

    @GetMapping("me/")
    public JsonNode checkUserInfo(User user){
        return userResponse.packResponse(user);
    }

}
