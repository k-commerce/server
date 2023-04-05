package com.kcommerce.domain.order.mapper;

import com.kcommerce.domain.order.domain.Order;
import com.kcommerce.domain.order.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "name", source = "orderDto.name")
    @Mapping(target = "phoneNumber", source = "orderDto.phoneNumber")
    @Mapping(target = "address.postcode", source = "orderDto.postcode")
    @Mapping(target = "address.selected", source = "orderDto.selected")
    @Mapping(target = "address.detailed", source = "orderDto.detailed")
    Order toEntity(Long memberId, OrderDto.Request orderDto);

    OrderDto.Response toDto(Order order);
}
