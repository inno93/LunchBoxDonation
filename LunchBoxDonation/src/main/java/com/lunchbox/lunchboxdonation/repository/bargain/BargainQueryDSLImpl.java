package com.lunchbox.lunchboxdonation.repository.bargain;


import com.lunchbox.lunchboxdonation.domain.bargain.BargainDTO;
import com.lunchbox.lunchboxdonation.entity.bargain.BargainSearch;
import com.lunchbox.lunchboxdonation.entity.bargain.QBargain;
import com.lunchbox.lunchboxdonation.entity.bargain.QBargainFile;
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

import static com.lunchbox.lunchboxdonation.entity.bargain.QBargain.bargain;
import static com.lunchbox.lunchboxdonation.entity.bargain.QBargainFile.bargainFile;

@RequiredArgsConstructor
@Slf4j
public class BargainQueryDSLImpl implements BargainQueryDSL {
    private final JPAQueryFactory query;
    @Override
    public Page<BargainDTO> bargainList(Pageable pageable, BargainSearch bargainSearch) {
        BooleanExpression bargainTitleContains = StringUtils.hasText(bargainSearch.getBargainTitle()) ? bargain.title.contains(bargainSearch.getBargainTitle()) : null;
        BooleanBuilder builder = new BooleanBuilder();

        if(bargainTitleContains != null){
            builder.or(bargainTitleContains);
        }

        final List<BargainDTO> bargainLists = query.select(
                Projections.fields(
                        BargainDTO.class,
                        bargain.title,
                        bargain.startDt,
                        bargain.endDt,
                        bargainFile.id,        // 필요한 BargainFile의 속성들을 여기에 추가
                        bargainFile.fileId,
                        bargainFile.filePath,
                        bargainFile.fileName,
                        bargain.createdAt
                )
        ).from(bargain)
                .leftJoin(bargain.bargainFile, bargainFile)
                .on(bargain.bargainFile.id.eq(bargainFile.id))
                .where(builder)
                .orderBy(bargain.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final long count = query.select(bargain.count()).from(bargain).fetchOne();
        return new PageImpl<>(bargainLists, pageable, count);
    }
}
