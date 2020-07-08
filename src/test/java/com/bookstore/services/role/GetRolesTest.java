package com.bookstore.services.role;

import com.bookstore.BookstoreApiApplication;
import com.bookstore.model.user.Role;
import com.bookstore.service.impl.RoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.reset;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BookstoreApiApplication.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class GetRolesTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoleServiceImpl roleService;

    @Test
    @WithMockUser(username = "admin@bookstore.com", roles={"ADMIN"})
    public void whenGetExistingRole_thenReturnRole() throws Exception {
        Role roleAdmin = Role.builder().id(1L).name("admin@bookstore.com").build();
        given(roleService.getRoles()).willReturn(Arrays.asList(roleAdmin));

        mvc.perform(get("/roles/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(roleAdmin.getName())));

        verify(roleService, VerificationModeFactory.times(1)).getRoles();
        reset(roleService);
    }
}
