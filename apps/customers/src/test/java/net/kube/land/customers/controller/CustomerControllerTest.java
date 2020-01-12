package net.kube.land.customers.controller;

import net.kube.land.customers.CustomersApplication;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomersApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
public class CustomerControllerTest {
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private RestDocumentationResultHandler document;

    @Before
    public void setUp() {

        this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.document)
                .build();
    }

    @Test
    public void list() throws Exception {
        this.mockMvc.perform(get("/kube/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("list-customers", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), responseFields(
                                fieldWithPath("[].customerId").type(JsonFieldType.STRING).optional().description("Customer Id"),
                                fieldWithPath("[].customerName").type(JsonFieldType.STRING).optional().description("Customer Name"),
                                fieldWithPath("[].customerPhone").type(JsonFieldType.STRING).optional().description("Customer Phone"),
                                fieldWithPath("[].customerEmail").type(JsonFieldType.STRING).optional().description("Customer EMail")
                        )
                )
        );
    }

    @Test
    public void getCustomer() throws Exception {
        this.mockMvc.perform(get("/kube/customers/500").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("get-customer", preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), responseFields(
                                fieldWithPath("customerId").type(JsonFieldType.STRING).optional().description("Customer Id"),
                                fieldWithPath("customerName").type(JsonFieldType.STRING).optional().description("Customer Name"),
                                fieldWithPath("customerPhone").type(JsonFieldType.STRING).optional().description("Customer Phone"),
                                fieldWithPath("customerEmail").type(JsonFieldType.STRING).optional().description("Customer EMail")
                        )
                )
        );
    }
}

