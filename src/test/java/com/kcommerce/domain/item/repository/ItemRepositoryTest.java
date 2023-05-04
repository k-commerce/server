package com.kcommerce.domain.item.repository;

import com.kcommerce.domain.item.domain.Item;
import com.kcommerce.domain.item.dto.ItemDto;
import com.kcommerce.test.config.DataJpaTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(DataJpaTestConfig.class)
@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    List<Item> itemList = new ArrayList<>();

    @BeforeEach
    void givenInit() {
        for (int i = 1; i < 11; i++) {
            Item item = Item
                    .builder()
                    .name("아이템" + i)
                    .price(i * 1000)
                    .description("아이템 설명" + i)
                    .build();
            itemList.add(item);
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("아이템 전체 불러오기")
    void testReadAll() {
        // given

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items.size()).isGreaterThan(0);
        assertThat(items).isNotNull();
    }

    @Test
    @DisplayName("Item Id로 조회")
    void testReadById() {
        // given
        Long callId = itemList.get(2).getId();

        // when
        Item calledItem = itemRepository.findById(callId).orElseThrow();

        // then
        assertThat(calledItem.getId()).isEqualTo(callId);
        assertThat(calledItem.getName()).isNotNull();
        assertThat(calledItem.getPrice()).isNotNull();
        assertThat(calledItem.getDescription()).isNotNull();
        assertThat(calledItem.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Item in 테스트")
    void testInById() {
        // given
        List<Long> idList = Arrays.asList(itemList.get(2).getId(), itemList.get(5).getId(), itemList.get(8).getId());

        // when
        List<Item> foundItem = itemRepository.findByIdIn(idList);

        // then
        assertThat(foundItem.size()).isGreaterThan(0);
        for (Item item : foundItem) {
            assertThat(item.getId()).isIn(idList);
        }
    }

    @Test
    @DisplayName("동적 쿼리 테스트 - 아이템 이름 검색")
    void testSearchItem_itemName() {
        // given
        ItemDto.ItemSearchCondition itemSearchCondition = new ItemDto.ItemSearchCondition();
        itemSearchCondition.setName("1");

        // when
        List<Item> foundItem = itemRepository.searchItem(itemSearchCondition);

        // then
        assertThat(foundItem.size()).isGreaterThan(0);
    }

}