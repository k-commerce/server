package com.kcommerce.domain.member.service;

import com.kcommerce.domain.member.domain.Member;
import com.kcommerce.domain.member.dto.MemberDto;
import com.kcommerce.domain.member.mapper.MemberMapper;
import com.kcommerce.domain.member.repository.MemberRepository;
import com.kcommerce.global.error.exception.BusinessException;
import com.kcommerce.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findByUsername(username)
                .map(member -> new MemberDto.MemberAdapter(String.valueOf(member.getId()), member.getPassword(), member))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public void join(MemberDto.JoinRequest request) {
        validateDuplicate(request.getUsername());
        Member member = memberMapper.toEntity(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        member.changePassword(encodedPassword);
        memberRepository.save(member);
    }

    private void validateDuplicate(String username) {
        memberRepository.findByUsername(username)
                .ifPresent(member -> {
                    throw new BusinessException(ErrorCode.USERNAME_DUPLICATE);
                });
    }
}
