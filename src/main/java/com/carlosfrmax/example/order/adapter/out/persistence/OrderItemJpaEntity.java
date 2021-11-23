package com.carlosfrmax.example.order.adapter.out.persistence;

import com.carlosfrmax.example.order.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "orderItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String productId;

    @NotNull
    @Column
    private Long quantity;

    @NotNull
    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderJpaEntity order;

    public OrderItemJpaEntity(String productId, Long quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}
