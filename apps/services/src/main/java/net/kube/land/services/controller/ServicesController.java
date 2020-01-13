package net.kube.land.services.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.services.dto.Service;
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
@RequestMapping("/kube/services")
public class ServicesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Service> services = Arrays.asList(
                new Service("100", "Marvel, Inc", "ACTIVE", "Commercial"),
                new Service("200", "Albert Einstein", "INACTIVE", "Personnel"),
                new Service("300", "Disney, Inc.", "ACTIVE", "Commercial"),
                new Service("400", "US Dept of Labor", "ACTIVE", "Government"),
                new Service("500", "UNICEF", "ACTIVE", "Non-Profit")
        );

        String responseString = objectMapper.writeValueAsString(services);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{serviceId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("serviceId") String serviceId) throws Exception {

        return new ResponseEntity(new Service(serviceId, "Albert Einstein", "INACTIVE", "Personnel"), HttpStatus.OK);
    }
}
