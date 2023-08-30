package com.lunchbox.lunchboxdonation.entity.bargain;

import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name="TBL_BARGAIN_FILE")
@NoArgsConstructor
public class BargainFile extends Timestamp {

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String fileId;
    private String filePath;
    private String fileName;

    @Builder
    public BargainFile(Long id, String fileId, String filePath, String fileName) {
        this.id = id;
        this.fileId = fileId;
        this.filePath = filePath;
        this.fileName = fileName;
    }
}
