package com.lunchbox.lunchboxdonation.entity.bargain;

import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name="TBL_BARGAIN_FILE")
public class BargainFile extends Timestamp {

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String fileId;
    private String filePath;
    private String fileName;



}
