package com.softserve.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.configuration.WebConfig;

import com.softserve.academy.model.User;
import com.softserve.academy.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfig.class })
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsersAction() throws Exception {

        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1, "nick", "1111"),
                new User(2, "mike", "2222")
        ));

        when(userServiceMock.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("nick")))
                .andExpect(jsonPath("$[0].password", is("1111")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("mike")))
                .andExpect(jsonPath("$[1].password", is("2222")));


        verify(userServiceMock, times(1)).getAllUsers();
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void testCreateUserAction() throws Exception {

        User user = new User(1, "nick", "1111");

        doNothing().when(userServiceMock).createUser(user);

        mockMvc.perform(post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated());

        verify(userServiceMock, times(1)).createUser(user);
        verifyNoMoreInteractions(userServiceMock);

    }

    @Test
    public void testUpdateUserAction() throws Exception {

        User user = new User(1, "nick", "111");

        doNothing().when(userServiceMock).updateUser(user);

        mockMvc.perform(
                put("/api/user/update", user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isNoContent());

        verify(userServiceMock, times(1)).updateUser(user);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void testDeleteUserAction() throws Exception {

        User user = new User(1, "nick", "111");

        doNothing().when(userServiceMock).deleteUser(user);
        mockMvc.perform(
                delete("/api/user/delete", user)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isNoContent());

        verify(userServiceMock, times(1)).deleteUser(user);
        verifyNoMoreInteractions(userServiceMock);
    }

}