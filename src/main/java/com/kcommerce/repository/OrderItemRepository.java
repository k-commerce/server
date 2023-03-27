package com.kcommerce.repository;

import com.kcommerce.domain.Member;
import com.kcommerce.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = "SELECT oi FROM OrderItem oi JOIN FETCH oi.order o JOIN FETCH oi.item i WHERE o.member = :member")
    List<OrderItem> findOrderItemByMember(@Param("member") Member member);
}
