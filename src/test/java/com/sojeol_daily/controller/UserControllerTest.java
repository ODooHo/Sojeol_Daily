package com.sojeol_daily.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sojeol_daily.domain.dto.UserDto;
import com.sojeol_daily.domain.dto.request.UserJoinRequest;
import com.sojeol_daily.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private final UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    UserControllerTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    @DisplayName("회원가입 요청이 오면 성공 응답을 반환한다.")
    public void givenNothing_whenJoining_thenSuccess() throws Exception {

        //given
        String userEmail = "test@test.com";
        String password = "1234";
        String nickname = "test";
        //when&then

        when(userService.join(userEmail, password, nickname)).thenReturn(mock(UserDto.class));
        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(UserJoinRequest.of(userEmail, password, nickname)))
                ).andDo(print())
                .andExpect(status().isOk());
    }
}