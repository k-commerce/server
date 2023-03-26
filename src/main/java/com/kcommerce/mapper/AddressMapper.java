package com.kcommerce.mapper;

import com.kcommerce.domain.Address;
import com.kcommerce.domain.AddressEntity;
import com.kcommerce.domain.Member;
import com.kcommerce.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "member", source = "member")
    @Mapping(target = "name", source = "addressDto.name")
    @Mapping(target = "address.postcode", source = "addressDto.postcode")
    @Mapping(target = "address.selected", source = "addressDto.selected")
    @Mapping(target = "address.detailed", source = "addressDto.detailed")
    AddressEntity toEntity(AddressDto addressDto, Member member);

    List<AddressDto> toDtoList(List<AddressEntity> addressEntityList);

    @Mapping(target = "postcode", source = "address.postcode")
    @Mapping(target = "selected", source = "address.selected")
    @Mapping(target = "detailed", source = "address.detailed")
    AddressDto toDto(AddressEntity addressEntity);

    Address toVo(AddressDto addressDto);
}
