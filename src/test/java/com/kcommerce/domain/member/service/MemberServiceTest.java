package com.kcommerce.domain.member.service;

import com.kcommerce.domain.member.domain.Member;
import com.kcommerce.domain.member.dto.MemberDto;
import com.kcommerce.domain.member.mapper.MemberMapper;
import com.kcommerce.domain.member.repository.MemberRepository;
import com.kcommerce.global.error.exception.BusinessException;
import com.kcommerce.global.error.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    MemberMapper memberMapper;
    PasswordEncoder passwordEncoder;
    Member member;

    @BeforeEach
    void setUp() {
        memberMapper = Mappers.getMapper(MemberMapper.class);
        passwordEncoder = new BCryptPasswordEncoder();
        memberService = new MemberService(memberRepository, memberMapper, passwordEncoder);
        member = Member.builder()
                .username("test")
                .password("test1234")
                .name("테스트")
                .phoneNumber("01012345678")
                .build();
    }

    @Test
    void 로그인_성공() {
        // given
        MemberDto.LoginRequest request = memberMapper.toLoginRequest(member);

        Member entity = memberMapper.toEntity(request);
        given(memberRepository.findByUsername(any())).willReturn(Optional.of(entity));

        // when
        UserDetails userDetails = memberService.loadUserByUsername(request.getUsername());

        String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
        boolean match = passwordEncoder.matches(request.getPassword(), encodedPassword);

        // then
        assertThat(match).isTrue();
    }

    @Test
    void 회원가입_성공() {
        // given
        MemberDto.JoinRequest request = memberMapper.toJoinRequest(member);

        given(memberRepository.findByUsername(any())).willReturn(Optional.empty());

        // when
        memberService.join(request);

        Member entity = memberMapper.toEntity(request);
        when(memberRepository.findByUsername(any())).thenReturn(Optional.of(entity));

        Member foundMember = memberRepository.findByUsername(request.getUsername()).orElseThrow();

        // then
        assertThat(foundMember.getUsername()).isEqualTo(request.getUsername());
        assertThat(foundMember.getName()).isEqualTo(request.getName());
        assertThat(foundMember.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
    }

    @Test
    void 회원가입_실패_이미존재하는아이디() {
        // given
        MemberDto.JoinRequest request = memberMapper.toJoinRequest(member);

        Member entity = memberMapper.toEntity(request);
        given(memberRepository.findByUsername(any())).willReturn(Optional.of(entity));

        // when
        BusinessException e = assertThrows(BusinessException.class, () -> memberService.join(request));

        // then
        assertThat(e.getMessage()).isEqualTo(ErrorCode.USERNAME_DUPLICATE.getMessage());
    }
}
