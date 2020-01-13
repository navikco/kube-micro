package net.kube.land.roles.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.roles.dto.Role;
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
@RequestMapping("/kube/roles")
public class RolesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Role> roles = Arrays.asList(
                new Role("100", "Marvel, Inc", "ACTIVE", "Commercial"),
                new Role("200", "Albert Einstein", "INACTIVE", "Personnel"),
                new Role("300", "Disney, Inc.", "ACTIVE", "Commercial"),
                new Role("400", "US Dept of Labor", "ACTIVE", "Government"),
                new Role("500", "UNICEF", "ACTIVE", "Non-Profit")
        );

        String responseString = objectMapper.writeValueAsString(roles);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("roleId") String roleId) throws Exception {

        return new ResponseEntity(new Role(roleId, "Albert Einstein", "INACTIVE", "Personnel"), HttpStatus.OK);
    }
}
