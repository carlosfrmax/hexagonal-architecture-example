package com.carlosfrmax.example.order.adapter.in.web;

import com.carlosfrmax.example.order.adapter.in.web.CreateOrderController;
import com.carlosfrmax.example.order.application.port.in.CreateOrderCommand;
import com.carlosfrmax.example.order.application.port.in.CreateOrderUseCase;

import com.carlosfrmax.example.order.domain.OrderItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CreateOrderController.class)
public class CreateOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateOrderUseCase createOrderUseCase;

    @Test
    void testCreateOrder() throws Exception {

        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem1 = new OrderItem("1", 2L, new BigDecimal("10"));
        orderItems.add(orderItem1);

        mockMvc.perform(post("/orders/create/{customerId}", "12345")
                .header("Content-Type", "application/json")
                .content(new ObjectMapper().writeValueAsString(orderItems)))
                .andExpect(status().isOk());

        CreateOrderCommand command = new CreateOrderCommand("12345", orderItems);
        then(createOrderUseCase).should()
                .createOrder(argThat(new CreateOrderCommandMatcher(command)));
    }

    class CreateOrderCommandMatcher implements ArgumentMatcher<CreateOrderCommand> {

        private CreateOrderCommand left;
        public CreateOrderCommandMatcher(CreateOrderCommand createOrderCommand) {
            this.left = createOrderCommand;
        }

        @Override
        public boolean matches(CreateOrderCommand right) {
            return left.getCustomerId().equals(right.getCustomerId())
                    && left.getOrderItems().size() == right.getOrderItems().size()
                    && left.getOrderItems().stream().findFirst().get().getProductId().equals(right.getOrderItems().stream().findFirst().get().getProductId());
        }
    }
}
