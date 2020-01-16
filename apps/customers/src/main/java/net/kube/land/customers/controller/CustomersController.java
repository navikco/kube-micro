package net.kube.land.customers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.customers.dto.Customer;
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
@RequestMapping("/kube/customers")
public class CustomersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomersController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Customer> customers = Arrays.asList(
                new Customer("100", "Tony Stark", "111.222.1000", "TStark@avengers.io"),
                new Customer("200", "Steve Rogers", "111.222.2000", "SRogers@avengers.io"),
                new Customer("300", "Natasha Romanoff", "111.222.3000", "NRomanoff@avengers.io"),
                new Customer("400", "Bruce Banner", "111.222.4000", "BBanner@avengers.io"),
                new Customer("500", "Clint Barton", "111.222.5000", "CBarton@avengers.io"),
                new Customer("600","Aniken Skywalker","111.222.6000","NFury@avengers.io")
        );

        String responseString = objectMapper.writeValueAsString(customers);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("customerId") String customerId) throws Exception {

        return new ResponseEntity(new Customer(customerId, "Steve Rogers", "111.222.2000", "SRogers@avengers.io"), HttpStatus.OK);
    }
}
