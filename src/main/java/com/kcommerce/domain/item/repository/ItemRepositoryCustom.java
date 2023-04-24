package com.kcommerce.domain.item.repository;

import com.kcommerce.domain.item.domain.Item;
import com.kcommerce.domain.item.dto.ItemDto;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> searchItem(ItemDto.ItemSearchCondition itemSearchCondition);
}
