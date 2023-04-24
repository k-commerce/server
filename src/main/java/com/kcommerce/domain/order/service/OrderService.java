package com.kcommerce.domain.order.service;

import com.kcommerce.domain.item.domain.Item;
import com.kcommerce.domain.item.repository.ItemRepository;
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

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final ItemRepository itemRepository;

    public void createOrder(OrderDto.Request request, Long memberId) {
        Map<Long, Integer> orderCheck = request.getOrderCheck();

        List<Long> itemIdList = new ArrayList<>(orderCheck.keySet());

        // 서비스 간 통신
        List<Item> itemList = itemRepository.findByIdIn(itemIdList);

        Order order = orderMapper.toEntity(memberId, request);
        orderRepository.save(order);

        for (Item item : itemList) {
            orderItemRepository.save(OrderItem.builder()
                    .itemId(item.getId())
                    .order(order)
                    .itemName(item.getName())
                    .quantity(orderCheck.get(item.getId()))
                    .orderPrice(item.getPrice() * orderCheck.get(item.getId()))
                    .status(OrderStatus.SUCCESS)
                    .build());
        }
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getOrderHistory(Long memberId) {
        List<OrderItem> orderItemList = orderItemRepository.findOrderItemByMemberId(memberId);

        Map<Order, List<OrderItem>> orderMap = orderItemList.stream()
                .collect(Collectors.groupingBy(OrderItem::getOrder));

        return orderMap.keySet()
                .stream()
                .map(order -> {
                    List<OrderItem> entityList = orderMap.get(order);
                    List<OrderItemDto> dtoList = orderItemMapper.toDtoList(entityList);
                    return orderMapper.toDto(order, dtoList);
                }).toList();
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
        if (!order.getMemberId().equals(memberId)) {
            throw new BusinessException(ErrorCode.CHECK_MEMBER);
        }
    }
}
