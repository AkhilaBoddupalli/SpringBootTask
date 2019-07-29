package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzix.MuzixApplication;
import com.stackroute.muzix.controller.UserController;
import com.stackroute.muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.muzix.model.User;
import com.stackroute.muzix.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
    @WebMvcTest
    @ContextConfiguration(classes = MuzixApplication.class)
    public class ControllerTest {

        @Autowired
        private MockMvc mockMvc;
        private User user;
        @MockBean
        private UserService userService;
        @InjectMocks
        private UserController userController;

        private List<User> list = null;

        @Before
        public void setUp() {

            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
            user = new User();
            user.setAge(16);
            user.setFirstName("Jonny");
            user.setId(101);
            user.setLastName("Jenny");

            list = new ArrayList();

            list.add(user);
        }

        @Test
        public void saveUser() throws Exception {
            when(userService.saveUser(any())).thenReturn(user);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                    .andExpect(status().isCreated())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void saveUserFailure() throws Exception {
            when(userService.saveUser(any())).thenThrow(UserAlreadyExistsException.class);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                    .andExpect(status().isConflict())
                    .andDo(MockMvcResultHandlers.print());


        }
        @Test
        public void getAllUser() throws Exception {
            when(userService.getAllUsers()).thenReturn(list);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print());

        }

        @Test
        public void deleteUser() throws Exception
        {

            when(userService.deleteUser(anyInt())).thenReturn(true);
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/1")
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                    .andExpect(status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        }
        @Test
        public void updateTrack() throws Exception {
            User user1 = new User(1,"testcase","testcomment",22);
            when(userService.updateUser(anyInt(), any())).thenReturn(user1);
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(asJsonString(user)))
                    .andExpect(status().isOk());
//        verify(trackService,times(1)).UpdateTrack(id,Mockito.any(Track.class));
        }




        private static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
