package com.lunchbox.lunchboxdonation.entity.Order;

import com.lunchbox.lunchboxdonation.domain.Order.OrderDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.Lunchbox;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "TBL_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;
    private int orderPrice;
    private int orderQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LUNCHBOX_ID")
    private Lunchbox lunchbox;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderAddress> orderAddresses = new ArrayList<>();

    @Builder
    public Order(OrderDTO orderDTO){
        this.orderPrice = orderDTO.getOrderPrice();
        this.orderQuantity = orderDTO.getOrderQuantity();
        this.orderDate = orderDTO.getOrderDate();
    }

    public static Order of(OrderDTO orderDTO){
        return Order.builder()
                .orderDTO(orderDTO)
                .build();
    }
}










