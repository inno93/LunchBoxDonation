package com.lunchbox.lunchboxdonation.repository.lunchbox;

import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.lunchbox.lunchboxdonation.entity.Lunchbox.QLunchBox.lunchBox;
import static com.lunchbox.lunchboxdonation.entity.Lunchbox.QLunchBoxOption.lunchBoxOption;

@RequiredArgsConstructor
@Slf4j
public class LunchBoxQueryDSLImpl implements LunchBoxQueryDSL {
    private final JPAQueryFactory query;

    @Override
    public Page<LunchBoxDTO> lunchBoxList(Pageable pageable, LunchBoxSearch lunchBoxSearch) {

        BooleanExpression lunchboxTitleContains = StringUtils.hasText(lunchBoxSearch.getLunchBoxTitle()) ? lunchBox.lunchboxTitle.contains(lunchBoxSearch.getLunchBoxTitle()) : null;


        BooleanBuilder builder = new BooleanBuilder();

        if(lunchboxTitleContains != null){
            builder.or(lunchboxTitleContains);
        }
        final List<LunchBoxDTO> lunchBoxs = query.select(
                Projections.fields(
                        LunchBoxDTO.class,
                        lunchBox.id,
                        lunchBox.lunchboxTitle,
                        lunchBox.lunchboxThumbNailingIMG,
                        lunchBox.price,
                        lunchBox.createdAt
                )
        ).from(lunchBox)
                .where(builder)
                .orderBy(lunchBox.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final long count = query.select(lunchBox.count()).from(lunchBox).fetchOne();
        return new PageImpl<>(lunchBoxs, pageable,count);
    }

    @Override
    public LunchBox lunchBoxDetail(Long id) {
        return   query.selectFrom(lunchBox)
                .leftJoin(lunchBox.lunchBoxOptions, lunchBoxOption).fetchJoin()
                .where(lunchBox.id.eq(id))
                .fetchOne();
    }
}
