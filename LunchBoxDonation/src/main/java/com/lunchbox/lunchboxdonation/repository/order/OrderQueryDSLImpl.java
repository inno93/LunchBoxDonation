package com.lunchbox.lunchboxdonation.repository.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderQueryDSLImpl implements OrderQueryDSL {
    private final JPAQueryFactory query;


}
