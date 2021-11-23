package com.carlosfrmax.example;

import com.carlosfrmax.example.order.domain.OrderItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CreateOrderSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createOrder() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem1 = new OrderItem("1", 2L, new BigDecimal("10"));
        orderItems.add(orderItem1);
        HttpEntity<String> request = new HttpEntity<>(new ObjectMapper().writeValueAsString(orderItems), headers);

        ResponseEntity response =  restTemplate.exchange(
                "/orders/create/{customerId}",
                HttpMethod.POST,
                request,
                Object.class,
                "12345");

        then(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

}
