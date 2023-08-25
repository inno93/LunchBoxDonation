package com.lunchbox.lunchboxdonation.service.lunchbox;

import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    // main MD Pick 추천 상품 리스트
    public List<LunchBox> getMainList(int limit, int offset);

    //깜짝 타임 세일
    public List<LunchBox> timeSaleList();


    public default LunchBox toEntity(LunchBoxDTO lunchBoxDTO){
        return LunchBox.builder()
                .id(lunchBoxDTO.getId())
                .lunchboxTitle(lunchBoxDTO.getLunchboxTitle())
                .lunchboxThumbNailingIMG(lunchBoxDTO.getLunchboxThumbNailingIMG())
                .price(lunchBoxDTO.getPrice())
                .lunchBoxOptions(lunchBoxDTO.getLunchBoxOptions())
                .build();
    }
}
