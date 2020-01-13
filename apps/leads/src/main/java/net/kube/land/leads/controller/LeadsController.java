package net.kube.land.leads.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.leads.dto.Lead;
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
@RequestMapping("/kube/leads")
public class LeadsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeadsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Lead> leads = Arrays.asList(
                new Lead("100", "1000", "100.10", "10100"),
                new Lead("200", "2000", "200.10", "20200"),
                new Lead("300", "3000", "300.10", "30300"),
                new Lead("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(leads);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{leadId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("leadId") String leadId) throws Exception {

        return new ResponseEntity(new Lead(leadId, leadId + "0", leadId + ".10", "10" + leadId), HttpStatus.OK);
    }
}
