package com.kcommerce.mapper;

import com.kcommerce.domain.Member;
import com.kcommerce.dto.AuthDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthDto.Response toResponse(Member member);
}
