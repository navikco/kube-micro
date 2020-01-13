package net.kube.land.categories.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.kube.land.categories.dto.Category;
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
@RequestMapping("/kube/categories")
public class CategoriesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> list() throws Exception {

        List<Category> categories = Arrays.asList(
                new Category("100", "1000", "100.10", "10100"),
                new Category("200", "2000", "200.10", "20200"),
                new Category("300", "3000", "300.10", "30300"),
                new Category("400", "4000", "400.10", "40400")
        );

        String responseString = objectMapper.writeValueAsString(categories);

        return new ResponseEntity(responseString, HttpStatus.OK);
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getCustomer(@PathVariable("categoryId") String categoryId) throws Exception {

        return new ResponseEntity(new Category(categoryId, categoryId + "0", categoryId + ".10", "10" + categoryId), HttpStatus.OK);
    }
}
