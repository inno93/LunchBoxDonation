package com.lunchbox.lunchboxdonation.entity.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "TBL_TEST")
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String testContent;
}
