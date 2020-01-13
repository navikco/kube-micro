package net.kube.land.orders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.orders.dto.Order;
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
@RequestMapping("/kube/orders")
public class OrdersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Order> orders = Arrays.asList(
                new Order("100", "02/12/2005", "1000", "100.10", "10100"),
                new Order("200", "12/18/2008", "2000", "200.10", "20200"),
                new Order("300", "10/30/2010", "3000", "300.10", "30300"),
                new Order("400", "01/11/2019", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(orders);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("orderId") String orderId) throws Exception {

        return new ResponseEntity(new Order(orderId, "01/01/2020", orderId + "0", orderId + ".10", "10" + orderId), HttpStatus.OK);
    }
}
