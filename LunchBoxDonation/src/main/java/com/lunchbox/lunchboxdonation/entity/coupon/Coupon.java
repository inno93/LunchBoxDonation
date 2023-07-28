package com.lunchbox.lunchboxdonation.entity.coupon;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull private String coupon;

    @Column
    @NotNull private Integer price;
}
