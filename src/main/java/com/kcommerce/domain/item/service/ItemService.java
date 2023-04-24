package com.kcommerce.domain.item.service;

import com.kcommerce.domain.item.domain.Item;
import com.kcommerce.domain.item.dto.ItemDto;
import com.kcommerce.domain.item.mapper.ItemMapper;
import com.kcommerce.domain.item.repository.ItemRepository;
import com.kcommerce.global.error.exception.BusinessException;
import com.kcommerce.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<ItemDto> getItemList(ItemDto.ItemSearchCondition itemSearchCondition) {
        List<Item> itemList = itemRepository.searchItem(itemSearchCondition);
        return itemMapper.toDtoList(itemList);
    }

    public ItemDto getItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ITEM_NOT_FOUND));
        return itemMapper.toDto(item);
    }
}
