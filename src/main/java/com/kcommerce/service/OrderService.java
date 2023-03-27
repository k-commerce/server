package com.kcommerce.service;

import com.kcommerce.domain.*;
import com.kcommerce.dto.OrderDto;
import com.kcommerce.dto.OrderItemDto;
import com.kcommerce.error.exception.BusinessException;
import com.kcommerce.error.exception.ErrorCode;
import com.kcommerce.mapper.OrderItemMapper;
import com.kcommerce.mapper.OrderMapper;
import com.kcommerce.repository.ItemRepository;
import com.kcommerce.repository.MemberRepository;
import com.kcommerce.repository.OrderItemRepository;
import com.kcommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    public void createOrder(OrderDto.Request orderDto, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        List<Item> itemList = itemRepository.findByIdIn(orderDto.getItemIdList());
        Order order = orderRepository.save(orderMapper.toEntity(member, orderDto));
        for (int i = 0; i < orderDto.getItemIdList().toArray().length; i++) {
            Item item = itemList.get(i);
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .order(order)
                    .status(OrderStatus.SUCCESS)
                    .quantity(orderDto.getCountList().get(i))
                    .orderPrice(orderDto.getTotalPriceList().get(i))
                    .build();
            orderItemRepository.save(orderItem);
        }
    }

    @Transactional(readOnly = true)
    public List<OrderItemDto.Response> getOrderHistory(Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        List<OrderItem> orderItemList = orderItemRepository.findOrderItemByMember(member);
        return orderItemMapper.toDtoList(orderItemList);
    }

    public void updateOrderItemStatus(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow();
        if (orderItem.getStatus() == OrderStatus.CANCEL) {
            throw new BusinessException(ErrorCode.ALREADY_CANCEL);
        }
        orderItem.updateStatus(OrderStatus.CANCEL);
    }
}
