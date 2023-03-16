package com.kcommerce.dto;

import com.kcommerce.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String name;
        private int depth;
        private List<Response> childList;

        public static Response of(Category category) {
            return new Response(
                    category.getName(),
                    category.getDepth(),
                    category.getChildList()
                            .stream()
                            .map(Response::of).collect(Collectors.toList())
            );
        }
    }



}
