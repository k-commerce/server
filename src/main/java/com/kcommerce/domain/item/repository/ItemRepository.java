package com.kcommerce.domain.item.repository;

import com.kcommerce.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByIdIn(List<Long> idList);
}
