package com.kcommerce.domain.order.service;

import com.kcommerce.domain.item.domain.Item;
import com.kcommerce.domain.item.dto.ItemDto;
import com.kcommerce.domain.item.mapper.ItemMapper;
import com.kcommerce.domain.item.repository.ItemRepository;
import com.kcommerce.domain.member.domain.Member;
import com.kcommerce.domain.member.repository.MemberRepository;
import com.kcommerce.domain.order.domain.Order;
import com.kcommerce.domain.order.domain.OrderItem;
import com.kcommerce.domain.order.domain.OrderStatus;
import com.kcommerce.domain.order.dto.OrderDto;
import com.kcommerce.domain.order.dto.OrderItemDto;
import com.kcommerce.domain.order.mapper.OrderItemMapper;
import com.kcommerce.domain.order.mapper.OrderMapper;
import com.kcommerce.domain.order.repository.OrderItemRepository;
import com.kcommerce.domain.order.repository.OrderRepository;
import com.kcommerce.global.error.exception.BusinessException;
import com.kcommerce.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        Map<Long, Integer> orderCheck = orderDto.getOrderCheck();

        List<Long> itemIdList = new ArrayList<>(orderCheck.keySet());
        List<Item> itemList = itemRepository.findByIdIn(itemIdList);

        Member member = memberRepository.getReferenceById(memberId);
        Order order = orderMapper.toEntity(member, orderDto);
        orderRepository.save(order);

        for (Item item : itemList) {
            orderItemRepository.save(OrderItem.builder()
                    .item(item)
                    .order(order)
                    .status(OrderStatus.SUCCESS)
                    .quantity(orderCheck.get(item.getId()))
                    .orderPrice(item.getPrice() * orderCheck.get(item.getId()))
                    .build());
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
