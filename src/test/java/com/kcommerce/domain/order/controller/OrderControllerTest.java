package com.kcommerce.domain.order.controller;

import com.kcommerce.domain.order.dto.OrderDto;
import com.kcommerce.domain.order.service.OrderService;
import com.kcommerce.global.config.SecurityConfig;
import com.kcommerce.global.config.security.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@Import(SecurityConfig.class)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @MockBean
    JwtProvider jwtProvider;

    @Test
    @DisplayName("[GET] 주문 기록")
    @WithMockUser
    void testGetOrder() throws Exception {
        // given
        List<OrderDto> orderDtoList = new ArrayList<>();
        OrderDto orderDto = OrderDto
                .builder()
                .id(1L)
                .name("user")
                .phoneNumber("01012345678")
                .build();
        orderDtoList.add(orderDto);
        given(orderService.getOrderHistory(any()))
                .willReturn(orderDtoList);

        // when & then
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("user"))
                .andExpect(jsonPath("$[0].phoneNumber").value("01012345678"));
    }
}