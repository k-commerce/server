package com.kcommerce.repository;

import com.kcommerce.domain.Item;
import com.kcommerce.dto.ItemSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.kcommerce.domain.QCategoryItem.categoryItem;

@RequiredArgsConstructor
public class CategoryItemRepositoryImpl implements CategoryItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Item> searchItem(ItemSearchCondition itemSearchCondition) {
        return queryFactory
                .select(categoryItem.item)
                .from(categoryItem)
                .where(
                        eqCategoryId(itemSearchCondition.getCategoryId()),
                        eqItemIdList(itemSearchCondition.getItemIdList())
                )
                .fetch();
    }

    private BooleanExpression eqCategoryId(Long categoryId) {
        return categoryId != null ? categoryItem.category.id.eq(categoryId) : null;
    }

    private BooleanExpression eqItemIdList(List<Long> itemIdList) {
        return itemIdList != null ? categoryItem.item.id.in(itemIdList) : null;
    }
}