package com.kcommerce.domain.item.repository;

import com.kcommerce.domain.item.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
