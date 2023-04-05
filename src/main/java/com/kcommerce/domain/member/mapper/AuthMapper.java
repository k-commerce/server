package com.kcommerce.domain.member.mapper;

import com.kcommerce.domain.member.domain.Member;
import com.kcommerce.domain.member.dto.AuthDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthDto.Response toResponse(Member member);
}
