package com.kcommerce.mapper;

import com.kcommerce.domain.Item;
import com.kcommerce.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public ItemDto.Response toDto(Item item) {
        return new ItemDto.Response(
                item.getName(),
                item.getPrice(),
                item.getDescription()
        );
    }

    public List<ItemDto.Response> toDtoList(List<Item> itemList) {
        return itemList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
