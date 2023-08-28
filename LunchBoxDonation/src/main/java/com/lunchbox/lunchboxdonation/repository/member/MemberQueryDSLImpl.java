package com.lunchbox.lunchboxdonation.repository.member;


import com.lunchbox.lunchboxdonation.domain.member.MemberDTO;
import com.lunchbox.lunchboxdonation.entity.member.MemberSearch;
import com.lunchbox.lunchboxdonation.entity.member.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.lunchbox.lunchboxdonation.entity.member.QMember.member;

@RequiredArgsConstructor
public class MemberQueryDSLImpl implements MemberQueryDSL {
    private final JPAQueryFactory query;

    @Override
    public Page<MemberDTO> memberList(Pageable pageable, MemberSearch memberSearch) {
        BooleanExpression memberIdContains = StringUtils.hasText(memberSearch.getMemberId()) ? member.memberId.contains(memberSearch.getMemberId()) : null;
        BooleanExpression memberNameContains = StringUtils.hasText(memberSearch.getMemberName()) ? member.memberName.contains(memberSearch.getMemberName()) : null;

        BooleanBuilder builder = new BooleanBuilder();

        if(memberIdContains != null) builder.or(memberIdContains);
        if(memberNameContains != null) builder.or(memberNameContains);


        final List<MemberDTO> members = query.select(
                Projections.fields(
                        MemberDTO.class,
                        member.id,
                        member.memberId,
                        member.memberName,
                        member.memberPhoneNumber,
                        member.memberEmail,
                        member.createdAt
                )
        ).from(member)
                .where(builder)
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final long count = query.select(member.count()).from(member).fetchOne();

        return new PageImpl<>(members, pageable, count);
    }
}
