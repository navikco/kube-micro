package net.kube.land.wishlists.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.wishlists.dto.Wishlist;
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
@RequestMapping("/kube/wishlists")
public class WishlistsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishlistsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Wishlist> wishlists = Arrays.asList(
                new Wishlist("100", "1000", "100.10", "10100"),
                new Wishlist("200", "2000", "200.10", "20200"),
                new Wishlist("300", "3000", "300.10", "30300"),
                new Wishlist("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(wishlists);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{wishlistId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("wishlistId") String wishlistId) throws Exception {

        return new ResponseEntity(new Wishlist(wishlistId, wishlistId + "0", wishlistId + ".10", "10" + wishlistId), HttpStatus.OK);
    }
}
