package com.jzw.template.api;

import com.jzw.template.api.dto.User;
import com.jzw.template.DAO.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ContextConfiguration
@TestPropertySource("/application-test.properties")
class UserApiV1Test {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    UserDAO userDAO;


    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        User user = new User();
        user.setId(1L);
        user.setName("jzw");
        userDAO.save(user);
        userDAO.saveInMongo(user);
    }

    /**
     * GIVEN: id = 1
     * WHEN: GET /api/v1/user/{id}
     * THEN: return {"id":1,"name":"jzw"}
     */
    @Test
    void shouldReturnUserJzw_GivenId1_WhenGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"name\":\"jzw\"}"))
                .andDo(MockMvcResultHandlers.print()
                );
    }

    @Test
    void shouldReturnUserJzw_GivenId1_WhenGetUserByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user?id=1&name=jzw")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"name\":\"jzw\"}"))
                .andDo(MockMvcResultHandlers.print()
                );
    }

    /**
     * GIVEN: id = 2
     * WHEN: GET /api/v1/user/{id}
     * THEN: return 404
     */
    @Test
    void shouldReturn404_GivenId2_WhenGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print()
                );
    }

    // must start another server first!
    @Test
    void shouldReturn200_GivenId1_WhenGetUserByFeign() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/feign")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()
                );
    }

}