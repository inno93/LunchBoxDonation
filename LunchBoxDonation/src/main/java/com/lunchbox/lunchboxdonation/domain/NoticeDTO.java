package com.lunchbox.lunchboxdonation.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class NoticeDTO {
    private Long id;
    private String title;
    private String contents;

}
