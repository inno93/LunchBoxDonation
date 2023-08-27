package com.lunchbox.lunchboxdonation.domain.Order;

import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Order.Order;
import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime orderDate;
    private int orderPrice;
    private int orderQuantity;
    private Member member;
    private LunchBox lunchbox;
    private List<OrderAddress> orderAddresses;


    @Builder
    public OrderResponseDTO(Order order, Member member, LunchBox lunchbox, List<OrderAddress> orderAddresses) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        this.orderPrice = order.getOrderPrice();
        this.orderQuantity = order.getOrderQuantity();
        this.lunchbox = lunchbox;
        this.orderAddresses = orderAddresses;
        this.member = member;

    }

    public static OrderResponseDTO of(Member member){
        return OrderResponseDTO.builder()
                .member(member)
                .build();
    };
}
