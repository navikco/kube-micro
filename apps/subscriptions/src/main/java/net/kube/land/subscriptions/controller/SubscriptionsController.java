package net.kube.land.subscriptions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.subscriptions.dto.Subscription;
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
@RequestMapping("/kube/subscriptions")
public class SubscriptionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Subscription> subscriptions = Arrays.asList(
                new Subscription("100", "1000", "100.10", "10100"),
                new Subscription("200", "2000", "200.10", "20200"),
                new Subscription("300", "3000", "300.10", "30300"),
                new Subscription("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(subscriptions);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{subscriptionId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("subscriptionId") String subscriptionId) throws Exception {

        return new ResponseEntity(new Subscription(subscriptionId, subscriptionId + "0", subscriptionId + ".10", "10" + subscriptionId), HttpStatus.OK);
    }
}
