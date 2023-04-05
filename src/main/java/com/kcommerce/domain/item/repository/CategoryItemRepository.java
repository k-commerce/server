package com.kcommerce.domain.item.repository;

import com.kcommerce.domain.item.domain.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long>, CategoryItemRepositoryCustom {
}
