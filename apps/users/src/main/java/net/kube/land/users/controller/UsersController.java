package net.kube.land.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/kube/users")
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<net.kube.land.users.dto.User> users = Arrays.asList(
            new net.kube.land.users.dto.User("100","Tony Stark","111.222.1000","TStark@avengers.io"),
            new net.kube.land.users.dto.User("200","Steve Rogers","111.222.2000","SRogers@avengers.io"),
            new net.kube.land.users.dto.User("300","Natasha Romanoff","111.222.3000","NRomanoff@avengers.io"),
            new net.kube.land.users.dto.User("400","Bruce Banner","111.222.4000","BBanner@avengers.io"),
            new net.kube.land.users.dto.User("500","Nick Fury","111.222.5000","NFury@avengers.io")
    );

        String responseString = objectMapper.writeValueAsString(users);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getUser(@PathVariable("userId") String userId) throws Exception {

        return new ResponseEntity(new net.kube.land.users.dto.User(userId,"Nick Fury","111.222.5000","NFury@avengers.io"), HttpStatus.OK);
    }
}
