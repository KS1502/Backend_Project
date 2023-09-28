package de.ait.bp.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Users is works")
class UsersControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProfileReturnsForbiddenForNotAuthenticatedUser() throws Exception {
        mockMvc.perform(post("/api/users/my"))
                .andExpect(status().isUnauthorized());
    }

    @WithUserDetails(value = "test@user.com")
    @Test
    @Sql(scripts = "/sql/data.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getProfileReturnsOkForAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/api/users/my"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}