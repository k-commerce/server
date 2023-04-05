package com.kcommerce.domain.order.mapper;

import com.kcommerce.domain.order.domain.OrderItem;
import com.kcommerce.domain.order.dto.OrderItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemDto.Response toDto(OrderItem orderItem);

    List<OrderItemDto.Response> toDtoList(List<OrderItem> orderItem);
}
