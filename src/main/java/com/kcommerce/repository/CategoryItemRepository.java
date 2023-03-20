package com.kcommerce.repository;

import com.kcommerce.domain.CategoryItem;
import com.kcommerce.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
    @Query(value = "SELECT c.item FROM CategoryItem c " +
            "WHERE c.category.id = :categoryId")
    List<Item> findItemByCategoryId(@Param("categoryId") Long categoryId);
}
