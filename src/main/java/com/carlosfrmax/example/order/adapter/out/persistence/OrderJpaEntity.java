package com.carlosfrmax.example.order.adapter.out.persistence;

import com.carlosfrmax.example.order.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "orderTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderJpaEntity {

    @Id
    @GeneratedValue
    private Long orderId;

    @Column
    private ZonedDateTime creationDate;

    @Column
    private String customerId;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderItemJpaEntity> orderItems;
}
