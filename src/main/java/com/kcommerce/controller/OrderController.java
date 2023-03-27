package com.kcommerce.controller;

import com.kcommerce.dto.OrderDto;
import com.kcommerce.dto.OrderItemDto;
import com.kcommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid OrderDto.Request orderDto,
                                            @AuthenticationPrincipal Long memberId) {
        orderService.createOrder(orderDto, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/orders")
    public ResponseEntity<List<OrderItemDto.Response>> getOrderHistory(@AuthenticationPrincipal Long memberId) {
        return ResponseEntity.ok(orderService.getOrderHistory(memberId));
    }

    @PutMapping("/api/orders/{orderItemId}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long orderItemId) {
        orderService.updateOrderItemStatus(orderItemId);
        return ResponseEntity.ok().build();
    }
}
