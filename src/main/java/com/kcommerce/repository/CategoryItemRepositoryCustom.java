package com.kcommerce.repository;

import com.kcommerce.domain.Item;
import com.kcommerce.dto.ItemSearchCondition;

import java.util.List;

public interface CategoryItemRepositoryCustom {

    List<Item> searchItem(ItemSearchCondition itemSearchCondition);
}
