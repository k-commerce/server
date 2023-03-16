package com.kcommerce.mapper;

import com.kcommerce.domain.Member;
import com.kcommerce.dto.MemberDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toEntity(MemberDto.JoinRequest request);
}
