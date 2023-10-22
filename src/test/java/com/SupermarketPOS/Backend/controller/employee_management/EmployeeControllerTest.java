package com.SupermarketPOS.Backend.controller.employee_management;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAllEmployeesQuery() throws Exception {
        String query = """
                query{
                allEmployees {                                
                            id
                            title
                            firstName
                            middleName
                            lastName
                            email
                            number
                            jobRole
                            active    
                    }
                }
                                
                """;
        String expectedResponse = """
                {
                    "data": {
                        "allEmployees": [
                            {
                                "id": "1",
                                "title": "MR",
                                "firstName": "Deo1",
                                "middleName": "john1",
                                "lastName": "Smith1",
                                "email": "Owner1@gmail.com",
                                "number": "1111111",
                                "jobRole": "OWNER",
                                "active": true
                            },
                            {
                                "id": "4",
                                "title": "MR",
                                "firstName": "Tharak",
                                "middleName": "Kumara",
                                "lastName": "kumara",
                                "email": "Admin1@gmail.com",
                                "number": "+0101111111",
                                "jobRole": "ADMIN",
                                "active": true
                            },
                            {
                                "id": "8",
                                "title": "MR",
                                "firstName": "Kobinarth",
                                "middleName": "",
                                "lastName": "Panchalingam",
                                "email": "kobinarth22@gmail.com",
                                "number": "0775694740",
                                "jobRole": "CASHIER",
                                "active": true
                            }
                        ]
                    }
                }
                """;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/graphql")
                        .content("{\"query\":\"" + query + "\"}")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals(expectedResponse, content);
    }

    // Add more test methods for other queries and mutations as needed
}
