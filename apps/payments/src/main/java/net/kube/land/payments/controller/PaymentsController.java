package net.kube.land.payments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.payments.dto.Payment;
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
@RequestMapping("/kube/payments")
public class PaymentsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Payment> payments = Arrays.asList(
                new Payment("100", "1000", "100.10", "10100"),
                new Payment("200", "2000", "200.10", "20200"),
                new Payment("300", "3000", "300.10", "30300"),
                new Payment("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(payments);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{paymentId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("paymentId") String paymentId) throws Exception {

        return new ResponseEntity(new Payment(paymentId, paymentId + "0", paymentId + ".10", "10" + paymentId), HttpStatus.OK);
    }
}
