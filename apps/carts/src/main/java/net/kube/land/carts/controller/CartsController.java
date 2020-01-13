package net.kube.land.carts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.carts.dto.Order;
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
@RequestMapping("/kube/carts")
public class CartsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Order> carts = Arrays.asList(
                new Order("100", "1000", "100.10", "10100"),
                new Order("200", "2000", "200.10", "20200"),
                new Order("300", "3000", "300.10", "30300"),
                new Order("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(carts);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("cartId") String cartId) throws Exception {

        return new ResponseEntity(new Order(cartId, cartId + "0", cartId + ".10", "10" + cartId), HttpStatus.OK);
    }
}
