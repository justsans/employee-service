package com.company.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;

    @Test
    public void shouldGetListOfEmployees() throws Exception {
        mockMvc.perform(get("/employee/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("employees")));
    }

    @Test
    public void shouldAddEmployee() throws Exception {
        addEmployee("Id11", "My Name")
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("My Name")))
                .andExpect(content().string(containsString("Id11")));
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
        addEmployee("Id11", "My Name");

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/delete/Id11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Id11")));
    }

    @Test
    public void userJourney() throws Exception {
        //add users
        addEmployee("Id11", "My Name");
        addEmployee("Id12", "My Name");

        //verify users are added
        mockMvc.perform(get("/employee/list"))
                .andExpect(content().string(containsString("Id11")))
                .andExpect(content().string(containsString("Id12")));

        //delete a user
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/delete/Id11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //verify users are deleted
        mockMvc.perform(get("/employee/list"))
                .andExpect(content().string(containsString("Id12")))
                .andExpect(content().string(not(containsString("Id11"))));

    }

    private ResultActions addEmployee(String id, String name) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/employee/add")
                .param("id", id)
                .param("name", name)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON));
    }

}
