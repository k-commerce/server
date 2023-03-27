package com.kcommerce.service;

import com.kcommerce.domain.Item;
import com.kcommerce.dto.ItemDto;
import com.kcommerce.error.exception.BusinessException;
import com.kcommerce.error.exception.ErrorCode;
import com.kcommerce.mapper.ItemMapper;
import com.kcommerce.repository.CategoryItemRepository;
import com.kcommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final CategoryItemRepository categoryItemRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<ItemDto.Response> getItemList(ItemDto.ItemSearchCondition itemSearchCondition) {
        List<Item> itemList = categoryItemRepository.searchItem(itemSearchCondition);
        if (itemList.isEmpty()) {
            throw new BusinessException(ErrorCode.ITEM_NOT_FOUND);
        }
        return itemMapper.toDtoList(itemList);
    }

    public ItemDto.Response getItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
        return itemMapper.toDto(item);
    }
}
