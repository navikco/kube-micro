package net.kube.land.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.accounts.dto.Account;
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
@RequestMapping("/kube/accounts")
public class AccountsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Account> accounts = Arrays.asList(
                new Account("100", "Marvel, Inc", "ACTIVE", "Commercial"),
                new Account("200", "Albert Einstein", "INACTIVE", "Personnel"),
                new Account("300", "Disney, Inc.", "ACTIVE", "Commercial"),
                new Account("400", "US Dept of Labor", "ACTIVE", "Government"),
                new Account("500", "UNICEF", "ACTIVE", "Non-Profit")
        );

        String responseString = objectMapper.writeValueAsString(accounts);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("accountId") String accountId) throws Exception {

        return new ResponseEntity(new Account(accountId, "Albert Einstein", "INACTIVE", "Personnel"), HttpStatus.OK);
    }
}
