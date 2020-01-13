package net.kube.land.dues.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.dues.dto.Due;
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
@RequestMapping("/kube/dues")
public class DuesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DuesController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Due> dues = Arrays.asList(
                new Due("100", "1000", "100.10", "10100"),
                new Due("200", "2000", "200.10", "20200"),
                new Due("300", "3000", "300.10", "30300"),
                new Due("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(dues);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{dueId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("dueId") String dueId) throws Exception {

        return new ResponseEntity(new Due(dueId, dueId + "0", dueId + ".10", "10" + dueId), HttpStatus.OK);
    }
}
