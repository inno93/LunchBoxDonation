package com.lunchbox.lunchboxdonation.service.lunchbox;

import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LunchBoxService {

    //추가
    public void lunchBoxInsert(LunchBoxDTO lunchBoxDTO);
    //목록
    public Page<LunchBoxDTO> lunchBoxList(Pageable pageable, LunchBoxSearch lunchBoxSearch);
    //수정
    //삭제


    public default LunchBox toEntity(LunchBoxDTO lunchBoxDTO){
        return LunchBox.builder()
                .lunchboxTitle(lunchBoxDTO.getLunchboxTitle())
                .lunchboxThumbNailingIMG(lunchBoxDTO.getLunchboxThumbNailingIMG())
                .price(lunchBoxDTO.getPrice())
                .build();
    }
}
