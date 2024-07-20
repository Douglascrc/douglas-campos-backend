package br.com.sysmap.bootcamp.domain.web;

import br.com.sysmap.bootcamp.domain.entities.Users;
import br.com.sysmap.bootcamp.domain.service.UsersService;
import br.com.sysmap.bootcamp.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.charset.Charset;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")

public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UsersService usersService;


    @Test
    @DisplayName("Should return users when valid users is saved")
    public void shouldReturnUsersWhenValidUsersIsSaved() throws Exception {
        Users users = Users.builder().id(1L).name("teste").email("test@mail.com").password("teste").build();
        when(usersService.create(any(),any())).thenReturn(new ResponseUserDto(users));
        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserRequestDto(users))).characterEncoding(Charset.defaultCharset()))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("/users/create/"+users.getId()));
    }

    @Test
    @DisplayName("Should return users when valid users is authenticated")
    public  void shouldReturnUsersWhenUsersAuthenticated() throws Exception {
        RequestAuthDto requestAuthDto = RequestAuthDto.builder().email("test@mail.com").password("test123").build();
        ResponseAuthDto responseAuthDto = ResponseAuthDto.builder().email("test@mail.com").id(1L).token("token").build();
        when(usersService.auth(any())).thenReturn(responseAuthDto);

        mockMvc.perform(post("/users/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestAuthDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@mail.com"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.token").value("token"))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    @DisplayName("Should return users  when users is updated")
    public void returnUsersWhenUsersUpdated() throws Exception {
        ResponseUserDto responseUserDto = new ResponseUserDto(1L,"User Test","test@mail.com");
        when(usersService.update(any())).thenReturn(responseUserDto);
        mockMvc.perform(put("/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserUpdateRequestDto(1L,"User test","test@mail.com","pass"))))
                .andExpect(status().isOk()).andExpect(jsonPath("$.email").value("test@mail.com"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("User Test"));
    }

    @Test
    @DisplayName("Should return all users like list")
    public void findAllShouldReturnOk() throws Exception {
        ResponseUserDto userDto = new ResponseUserDto(1L,"User Test","emailtest@mail.com");
        when(usersService.findAll(any())).thenReturn(new PageImpl<>(Collections.singletonList(userDto)));

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].id").value(userDto.getId()))
                .andExpect(jsonPath("$.content[0].name").value(userDto.getName()))
                .andExpect(jsonPath("$.content[0].email").value(userDto.getEmail()));
    }


    @Test
    @DisplayName("Should return user by id")
    public void returnUserById() throws Exception {
        ResponseUserDto userDto = new ResponseUserDto(1L, "User Test", "test@mail.com");
        when(usersService.findById(any())).thenReturn(userDto);

        mockMvc.perform(get("/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("User Test"));

    }

}
