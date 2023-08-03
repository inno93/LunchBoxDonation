package com.lunchbox.lunchboxdonation.entity.Order;

import com.lunchbox.lunchboxdonation.domain.Order.OrderDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.Lunchbox;
import com.lunchbox.lunchboxdonation.entity.Member;
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
    private Long order_id;

    @Column(nullable = false)
    private LocalDateTime order_date;
    private int order_price;
    private int order_quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LUNCHBOX_NUM")
    private Lunchbox lunchbox;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems = new ArrayList<>();

    @Builder
    public Order(OrderDTO orderDTO){
        this.order_price = orderDTO.getOrder_price();
        this.order_quantity = orderDTO.getOrder_quantity();
        this.order_date = orderDTO.getOrder_date();
    }

    public static Order of(OrderDTO orderDTO){
        return Order.builder()
                .orderDTO(orderDTO)
                .build();
    }
}










