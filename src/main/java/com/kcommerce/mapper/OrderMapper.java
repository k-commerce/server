package com.kcommerce.mapper;

import com.kcommerce.domain.Address;
import com.kcommerce.domain.Member;
import com.kcommerce.domain.Order;
import com.kcommerce.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final AddressMapper addressMapper;

    public Order toEntity(Member member, OrderDto.Request orderDto) {
        return Order.builder()
                .member(member)
                .address(toVo(orderDto))
                .payment(orderDto.getPayment())
                .name(orderDto.getName())
                .phoneNumber(orderDto.getPhoneNumber())
                .build();
    }

    public OrderDto.Response toDto(Order order) {
        return new OrderDto.Response(
                order.getId(),
                order.getPayment(),
                order.getCreatedDate(),
                order.getName(),
                order.getPhoneNumber(),
                order.getAddress()
        );
    }

    public Address toVo(OrderDto.Request orderDto) {
        return Address.builder()
                .postcode(orderDto.getPostcode())
                .selected(orderDto.getSelected())
                .detailed(orderDto.getDetailed())
                .build();
    }
}
