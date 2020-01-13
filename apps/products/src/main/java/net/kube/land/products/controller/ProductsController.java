package net.kube.land.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.products.dto.Product;
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
@RequestMapping("/kube/products")
public class ProductsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Product> products = Arrays.asList(
                new Product("100", "Skateboard", "10", "SPORTS", "ACTIVE", "100"),
                new Product("100", "Medidating Buddha", "10", "HOME", "ACTIVE", "20"),
                new Product("100", "Birdman of Alctraz", "10", "MOVIES", "INACTIVE", "0"),
                new Product("100", "The Logic of Failure", "10", "BOOKS", "ACTIVE", "50")
        );

        String responseString = objectMapper.writeValueAsString(products);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("productId") String productId) throws Exception {

        return new ResponseEntity(new Product(productId, "Medidating Buddha", productId + ".10", "HOME", "ACTIVE", productId + "10"), HttpStatus.OK);
    }
}
