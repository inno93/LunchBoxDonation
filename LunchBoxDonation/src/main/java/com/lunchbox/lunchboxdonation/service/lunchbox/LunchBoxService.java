package com.lunchbox.lunchboxdonation.service.lunchbox;

import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LunchBoxService {

    //추가
    public Long lunchBoxInsert(LunchBoxDTO lunchBoxDTO);
    //목록
    public Page<LunchBoxDTO> lunchBoxList(Pageable pageable, LunchBoxSearch lunchBoxSearch);
    //상세보기
    public LunchBox getLunchBoxWithOptionByLunchBoxId(Long id);

    //이미지 파일명 조회
    public String getImgName(Long id);

    //수정
    public Long LunchBoxUpdate(LunchBoxDTO lunchBoxDTO);


    //삭제
    public void deleteLunchBoxAndOptionsByLunchBoxId(Long id);


    public default LunchBox toEntity(LunchBoxDTO lunchBoxDTO){
        return LunchBox.builder()
                .lunchboxTitle(lunchBoxDTO.getLunchboxTitle())
                .lunchboxThumbNailingIMG(lunchBoxDTO.getLunchboxThumbNailingIMG())
                .price(lunchBoxDTO.getPrice())
                .lunchBoxOptions(lunchBoxDTO.getLunchBoxOptions())
                .build();
    }
}
