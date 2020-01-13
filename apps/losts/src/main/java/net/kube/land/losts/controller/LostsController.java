package net.kube.land.losts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.losts.dto.Lost;
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
@RequestMapping("/kube/losts")
public class LostsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LostsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Lost> losts = Arrays.asList(
                new Lost("100", "1000", "100.10", "10100"),
                new Lost("200", "2000", "200.10", "20200"),
                new Lost("300", "3000", "300.10", "30300"),
                new Lost("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(losts);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{lostId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("lostId") String lostId) throws Exception {

        return new ResponseEntity(new Lost(lostId, lostId + "0", lostId + ".10", "10" + lostId), HttpStatus.OK);
    }
}
