package com.kcommerce.mapper;

import com.kcommerce.domain.Address;
import com.kcommerce.domain.AddressEntity;
import com.kcommerce.domain.Member;
import com.kcommerce.dto.AddressDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public AddressEntity toEntity(AddressDto addressDto, Member member) {
        return AddressEntity.builder()
                .member(member)
                .name(addressDto.getName())
                .address(toVo(addressDto))
                .build();
    }

    public List<AddressDto> toDtoList(List<AddressEntity> addressEntityList) {
        return addressEntityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AddressDto toDto(AddressEntity addressEntity) {
        return AddressDto.builder()
                .id(addressEntity.getId())
                .name(addressEntity.getName())
                .postcode(addressEntity.getAddress().getPostcode())
                .selected(addressEntity.getAddress().getSelected())
                .detailed(addressEntity.getAddress().getDetailed())
                .build();
    }

    public Address toVo(AddressDto addressDto) {
        return Address.builder()
                .postcode(addressDto.getPostcode())
                .selected(addressDto.getSelected())
                .detailed(addressDto.getDetailed())
                .build();
    }
}
