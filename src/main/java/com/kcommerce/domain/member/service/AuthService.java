package com.kcommerce.domain.member.service;

import com.kcommerce.domain.member.dto.AuthDto;
import com.kcommerce.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findByUsername(username)
                .map(member -> new AuthDto.MemberAdapter(String.valueOf(member.getId()), member.getPassword(), member))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
