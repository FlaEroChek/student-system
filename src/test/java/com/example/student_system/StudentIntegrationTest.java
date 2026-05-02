package com.example.student_system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void createStudent() throws Exception {

        String json = """
            {
              "fullName":"Ivan",
              "email":"ivan@test.com",
              "groupName":"IT-21",
              "averageGrade":4.5
            }
        """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAllStudents() throws Exception {
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "fullName":"Test User",
                              "email":"test@test.com",
                              "groupName":"IT-21",
                              "averageGrade":4.5
                            }
                            """))
                .andExpect(status().isOk());

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/students"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestOnDuplicateEmail() throws Exception {
        String json = """
            {
              "fullName":"Ivan",
              "email":"duplicate@test.com",
              "groupName":"IT-21",
              "averageGrade":4.5
            }
            """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}