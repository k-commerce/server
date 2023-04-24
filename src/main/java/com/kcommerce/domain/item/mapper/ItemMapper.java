package com.kcommerce.domain.item.mapper;

import com.kcommerce.domain.item.domain.Item;
import com.kcommerce.domain.item.dto.ItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);

    List<ItemDto> toDtoList(List<Item> itemList);
}
