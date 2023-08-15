package com.lunchbox.lunchboxdonation.entity.special;

import com.lunchbox.lunchboxdonation.entity.Order.Order;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Likes extends Timestamp {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Builder
    public Likes(Member member, Order order){
        this.member = member;
        this.order = order;
    }

    public static Likes of(Member member, Order order){
        return Likes.builder()
                .member(member)
                .order(order)
                .build();
    }
}
