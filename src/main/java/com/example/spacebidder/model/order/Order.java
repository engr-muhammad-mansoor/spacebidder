package com.example.spacebidder.model.order;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
    private LocalDateTime created;

    @OneToMany
    @JoinColumn()
    private List<OrderItem> orderItems;
}

