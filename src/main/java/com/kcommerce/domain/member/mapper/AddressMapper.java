package com.kcommerce.domain.member.mapper;

import com.kcommerce.domain.member.domain.Address;
import com.kcommerce.domain.member.domain.AddressEntity;
import com.kcommerce.domain.member.domain.Member;
import com.kcommerce.domain.member.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "postcode", source = "address.postcode")
    @Mapping(target = "selected", source = "address.selected")
    @Mapping(target = "detailed", source = "address.detailed")
    AddressDto toDto(AddressEntity addressEntity);

    List<AddressDto> toDtoList(List<AddressEntity> addressEntityList);

    @Mapping(target = "member", source = "member")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "address.postcode", source = "request.postcode")
    @Mapping(target = "address.selected", source = "request.selected")
    @Mapping(target = "address.detailed", source = "request.detailed")
    AddressEntity toEntity(AddressDto.Request request, Member member);

    Address toVo(AddressDto.Request request);
}
