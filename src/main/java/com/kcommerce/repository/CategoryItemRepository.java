package com.kcommerce.repository;

import com.kcommerce.domain.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long>, CategoryItemRepositoryCustom {
}
