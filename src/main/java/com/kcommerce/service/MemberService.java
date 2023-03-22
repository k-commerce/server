package com.kcommerce.service;

import com.kcommerce.domain.Member;
import com.kcommerce.dto.MemberDto;
import com.kcommerce.error.exception.BusinessException;
import com.kcommerce.error.exception.ErrorCode;
import com.kcommerce.mapper.MemberMapper;
import com.kcommerce.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    @Transactional
    public void join(MemberDto.JoinRequest request) {
        validateDuplicate(request.getUsername());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);

        Member member = memberMapper.toEntity(request);
        memberRepository.save(member);
    }

    private void validateDuplicate(String username) {
        memberRepository.findByUsername(username)
                .ifPresent(member -> {
                    throw new BusinessException(ErrorCode.USERNAME_DUPLICATE);
                });
    }
}
