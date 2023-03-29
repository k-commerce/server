package com.kcommerce.service;

import com.kcommerce.domain.*;
import com.kcommerce.dto.ItemDto;
import com.kcommerce.dto.OrderDto;
import com.kcommerce.dto.OrderItemDto;
import com.kcommerce.error.exception.BusinessException;
import com.kcommerce.error.exception.ErrorCode;
import com.kcommerce.mapper.ItemMapper;
import com.kcommerce.mapper.OrderItemMapper;
import com.kcommerce.mapper.OrderMapper;
import com.kcommerce.repository.ItemRepository;
import com.kcommerce.repository.MemberRepository;
import com.kcommerce.repository.OrderItemRepository;
import com.kcommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ItemMapper itemMapper;

    public void createOrder(OrderDto.Request orderDto, Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        List<Long> itemIdList = new ArrayList<>();
        for (OrderDto.OrderCheck item : orderDto.getOrderItemList()) {
            itemIdList.add(item.getItemId());
        }
        List<Item> itemList = itemRepository.findByIdIn(itemIdList);
        Order order = orderRepository.save(orderMapper.toEntity(member, orderDto));
        for (int i = 0; i < itemIdList.toArray().length; i++) {
            Item item = itemList.get(i);
            int quantity = orderDto.getOrderItemList().get(i).getQuantity();
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .order(order)
                    .status(OrderStatus.SUCCESS)
                    .quantity(quantity)
                    .orderPrice(itemMapper.toDto(item).getPrice() * quantity)
                    .build();
            orderItemRepository.save(orderItem);
        }
    }

    @Transactional(readOnly = true)
    public List<OrderItemDto.Response> getOrderHistory(Long memberId) {
        Member member = memberRepository.getReferenceById(memberId);
        List<OrderItem> orderItemList = orderItemRepository.findOrderItemByMember(member);
        return orderItemList
                .stream()
                .map(orderItem -> {
                    OrderDto.Response orderDto = orderMapper.toDto(orderItem.getOrder());
                    ItemDto.Response itemDto = itemMapper.toDto(orderItem.getItem());
                    return orderItemMapper.toDto(orderItem, orderDto, itemDto);
                })
                .collect(Collectors.toList());
    }

    public void updateOrderItemStatus(Long memberId, Long orderId, Long orderItemId) {
        checkMember(memberId, orderId);
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_ITEM_NOT_FOUND));
        if (orderItem.getStatus() == OrderStatus.CANCEL) {
            throw new BusinessException(ErrorCode.ALREADY_CANCEL);
        }
        orderItem.updateStatus(OrderStatus.CANCEL);
    }

    private void checkMember(Long memberId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));
        if (!order.getMember().getId().equals(memberId)) {
            throw new BusinessException(ErrorCode.CHECK_MEMBER);
        }
    }
}
