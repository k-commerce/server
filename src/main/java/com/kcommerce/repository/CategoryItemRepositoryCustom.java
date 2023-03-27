package com.kcommerce.repository;

import com.kcommerce.domain.Item;
import com.kcommerce.dto.ItemDto;

import java.util.List;

public interface CategoryItemRepositoryCustom {

    List<Item> searchItem(ItemDto.ItemSearchCondition itemSearchCondition);
}
