package com.example.usersservice.infrastructure.input.rest;

import com.example.usersservice.application.dto.UserRequest;
import com.example.usersservice.domain.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserById() throws Exception {
        Long userId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + userId))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserByEmail() throws Exception {
        String email = "alex@gmail.com";
        mockMvc.perform(MockMvcRequestBuilders.get("/user/email/" + email))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveUser() throws Exception {
        UserRequest userRequest = new UserRequest(); // Crea una instancia de UserRequest válida
        String requestBody = objectMapper.writeValueAsString(userRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteUser() throws Exception {
        Long userId = 1L; // Reemplaza con un ID válido
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/" + userId))
                .andExpect(status().isOk());
    }
}