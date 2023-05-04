package com.kcommerce.domain.member.mapper;

import com.kcommerce.domain.member.domain.Member;
import com.kcommerce.domain.member.dto.MemberDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDto toDto(Member member);

    Member toEntity(MemberDto.LoginRequest request);

    Member toEntity(MemberDto.JoinRequest request);

    MemberDto.LoginRequest toLoginRequest(Member member);

    MemberDto.JoinRequest toJoinRequest(Member member);
}
