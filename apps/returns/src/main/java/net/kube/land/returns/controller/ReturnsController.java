package net.kube.land.returns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.returns.dto.Return;
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
@RequestMapping("/kube/returns")
public class ReturnsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Return> returns = Arrays.asList(
                new Return("100", "1000", "100.10", "10100"),
                new Return("200", "2000", "200.10", "20200"),
                new Return("300", "3000", "300.10", "30300"),
                new Return("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(returns);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{returnId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("returnId") String returnId) throws Exception {

        return new ResponseEntity(new Return(returnId, returnId + "0", returnId + ".10", "10" + returnId), HttpStatus.OK);
    }
}
