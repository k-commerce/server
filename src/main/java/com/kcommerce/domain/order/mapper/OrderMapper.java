package com.kcommerce.domain.order.mapper;

import com.kcommerce.domain.order.domain.Order;
import com.kcommerce.domain.order.dto.OrderDto;
import com.kcommerce.domain.order.dto.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "name", source = "orderDto.name")
    @Mapping(target = "phoneNumber", source = "orderDto.phoneNumber")
    @Mapping(target = "address.postcode", source = "orderDto.postcode")
    @Mapping(target = "address.selected", source = "orderDto.selected")
    @Mapping(target = "address.detailed", source = "orderDto.detailed")
    Order toEntity(Long memberId, OrderDto.Request orderDto);

    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "name", source = "order.name")
    @Mapping(target = "phoneNumber", source = "order.phoneNumber")
    @Mapping(target = "address", source = "order.address")
    @Mapping(target = "payment", source = "order.payment")
    @Mapping(target = "createdDate", source = "order.createdDate")
    @Mapping(target = "orderItemList", source = "orderItemDtoList")
    OrderDto.Response toDto(Order order, List<OrderItemDto.Response> orderItemDtoList);
}
