package com.kcommerce.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcommerce.domain.member.dto.MemberDto;
import com.kcommerce.domain.member.mapper.MemberMapper;
import com.kcommerce.domain.member.service.MemberService;
import com.kcommerce.global.config.SecurityConfig;
import com.kcommerce.global.config.security.JwtProvider;
import com.kcommerce.global.error.exception.BusinessException;
import com.kcommerce.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@Import(SecurityConfig.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberMapper memberMapper;

    @MockBean
    JwtProvider jwtProvider;

    @Test
    void 회원가입_성공() throws Exception {
        // given
        MemberDto.JoinRequest request = MemberDto.JoinRequest.builder()
                .username("test")
                .password("test1234")
                .name("테스트")
                .phoneNumber("01012345678")
                .build();

        willDoNothing().given(memberService).join(any());

        // when
        ResultActions resultActions =
                mockMvc.perform(post("/api/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                        .andDo(print());

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void 회원가입_실패_이미존재하는아이디() throws Exception {
        // given
        MemberDto.JoinRequest request = MemberDto.JoinRequest.builder()
                .username("test")
                .password("test1234")
                .name("테스트")
                .phoneNumber("01012345678")
                .build();

        willThrow(new BusinessException(ErrorCode.USERNAME_DUPLICATE)).given(memberService).join(any());

        // when
        ResultActions resultActions =
                mockMvc.perform(post("/api/members")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                        .andDo(print());

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}
