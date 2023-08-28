package com.lunchbox.lunchboxdonation.entity.member;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString
public class MemberSearch {
    private String memberId;
    private String memberName;
}
