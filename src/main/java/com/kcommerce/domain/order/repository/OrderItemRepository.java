package com.kcommerce.domain.order.repository;

import com.kcommerce.domain.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = "SELECT oi" +
            " FROM OrderItem oi" +
            " JOIN FETCH oi.order o" +
            " WHERE o.memberId = :memberId")
    List<OrderItem> findOrderItemByMemberId(@Param("memberId") Long memberId);
}
