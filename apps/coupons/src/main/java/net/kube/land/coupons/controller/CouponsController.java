package net.kube.land.coupons.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.coupons.dto.Coupon;
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
@RequestMapping("/kube/coupons")
public class CouponsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Coupon> coupons = Arrays.asList(
                new Coupon("100", "1000", "100.10", "10100"),
                new Coupon("200", "2000", "200.10", "20200"),
                new Coupon("300", "3000", "300.10", "30300"),
                new Coupon("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(coupons);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{couponId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("couponId") String couponId) throws Exception {

        return new ResponseEntity(new Coupon(couponId, couponId + "0", couponId + ".10", "10" + couponId), HttpStatus.OK);
    }
}
