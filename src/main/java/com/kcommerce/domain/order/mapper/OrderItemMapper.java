package com.kcommerce.domain.order.mapper;

import com.kcommerce.domain.item.dto.ItemDto;
import com.kcommerce.domain.order.domain.OrderItem;
import com.kcommerce.domain.order.dto.OrderDto;
import com.kcommerce.domain.order.dto.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "id", source = "orderItem.id")
    @Mapping(target = "order", source = "orderDto")
    @Mapping(target = "item", source = "itemDto")
    @Mapping(target = "status", source = "orderItem.status")
    @Mapping(target = "quantity", source = "orderItem.quantity")
    @Mapping(target = "orderPrice", source = "orderItem.orderPrice")
    OrderItemDto.Response toDto(OrderItem orderItem, OrderDto.Response orderDto, ItemDto.Response itemDto);
}
