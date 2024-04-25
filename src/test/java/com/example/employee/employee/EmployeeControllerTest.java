package com.example.employee.employee;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void EmployeeController_GetAllEmployees_ReturnsMoreThanOneEmployee() throws Exception {
        Employee employee = new Employee("Tom", "Cruise");
        List<Employee> employees = Arrays.asList(employee);
        Mockito.when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Tom")))
                .andExpect(jsonPath("$[0].lastName", Matchers.is("Cruise")));
    }

}