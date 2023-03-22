package com.kcommerce.service;

import com.kcommerce.domain.Item;
import com.kcommerce.dto.ItemDto;
import com.kcommerce.mapper.ItemMapper;
import com.kcommerce.repository.CategoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final CategoryItemRepository categoryItemRepository;
    private final ItemMapper itemMapper;

    public List<ItemDto.Response> getItemList(Long categoryId) {
        List<Item> itemList = categoryItemRepository.findItemByCategoryId(categoryId);
        return itemMapper.toDtoList(itemList);
    }

}
