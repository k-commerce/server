package com.kcommerce.mapper;

import com.kcommerce.domain.OrderItem;
import com.kcommerce.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;

    public OrderItemDto.Response toDto(OrderItem orderItem) {
        return new OrderItemDto.Response(
                orderItem.getId(),
                orderMapper.toDto(orderItem.getOrder()),
                itemMapper.toDto(orderItem.getItem()),
                orderItem.getStatus(),
                orderItem.getQuantity(),
                orderItem.getOrderPrice()
        );
    }

    public List<OrderItemDto.Response> toDtoList(List<OrderItem> orderItemList) {
        return orderItemList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
