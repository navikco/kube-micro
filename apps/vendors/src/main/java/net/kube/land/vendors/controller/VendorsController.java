package net.kube.land.vendors.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.vendors.dto.Vendor;
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
@RequestMapping("/kube/vendors")
public class VendorsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorsController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Vendor> vendors = Arrays.asList(
                new Vendor("100", "1000", "100.10", "10100"),
                new Vendor("200", "2000", "200.10", "20200"),
                new Vendor("300", "3000", "300.10", "30300"),
                new Vendor("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(vendors);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{vendorId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("vendorId") String vendorId) throws Exception {

        return new ResponseEntity(new Vendor(vendorId, vendorId + "0", vendorId + ".10", "10" + vendorId), HttpStatus.OK);
    }
}
