package com.dish.springplayground.controllers;

import com.dish.springplayground.config.SecurityConfig;
import com.dish.springplayground.repositories.EmployeeRepository;
import com.dish.springplayground.services.EmployeeDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@Import(SecurityConfig.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeRepository repository;

    @MockBean
    EmployeeDetailsService employeeDetailsService;

    @Before
    public void setup() {
        when(repository.findAll())
                .thenReturn(emptyList());
    }

//    @Test
//    public void testWithUser() throws Exception {
//        RequestBuilder request = get("/employees/admin/employees")
//                .with(user("user").roles("USER"));
//
//        mockMvc.perform(request)
//                .andExpect(status().isUnauthorized());
//    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void indexAllowsManagerUsers() throws Exception {
        RequestBuilder request = get("/employees");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void indexDisallowsAnonymousUsers() throws Exception {
        RequestBuilder request = get("/employees").with(anonymous());

        mockMvc.perform(request).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    public void getMeSample() throws Exception {
        RequestBuilder request = get("/employees/me");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

}