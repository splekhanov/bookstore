package com.example.bookstore.controllerintegration.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class GetUserTest {

    @Autowired
    private MockMvc mvc;

    private String token;

//    @MockBean
//    private UserRepository userRepository;
//
//    @Before
//    public void setUp() {
//        User user1 = User.builder()
//                .name("user1@epam.com")
//                .password("1234")
//                .roles(Arrays.asList(Role.builder().id(2L).build())).build();
//
//        Mockito.when(userRepository.findByName(user1.getName()))
//                .thenReturn(java.util.Optional.of(user1));
//    }

    @Before
    public void setUp(){
        token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjEsXCJuYW1lXCI6XCJhZG1pbkBlcGFtLmNvbVwiLFwicGFzc3dvcmRcIjpcIiQyYSQxMCRCRWViSlYyV0NVUG5ka0x2WWlEYkIuMU1tdnNkSXZjZFFJaEQ2MG1qL0lYbXk5UWFVY0JXeVwiLFwicm9sZXNcIjpbXX0iLCJpYXQiOjE1OTE3Njc4ODAsImV4cCI6MTU5MTc5NjY4MH0.5cxhqdIUlyijya08JBjYuQme2HKMd1H0EeELnY7xpGmk1NSNv7HFmUJ540XERuNALmBgQzaTdCGGOZWfDKoUEQ";
    }

    @Test
    public void getUser_byExistingUsername_shouldReturnUserObjectAnd200() throws Exception {
        mvc.perform(get("/users/1")
                .header("authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("admin@epam.com")));
    }
}
