package com.lunchbox.lunchboxdonation.service.bargain;

import com.lunchbox.lunchboxdonation.domain.bargain.BargainDTO;
import com.lunchbox.lunchboxdonation.entity.bargain.Bargain;
import com.lunchbox.lunchboxdonation.entity.bargain.BargainSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BargainService {

    public Long bargainInsert(BargainDTO bargainDTO);

    public Page<BargainDTO> getBargainList(Pageable pageable, BargainSearch bargainSearch);



    public default Bargain toEntity(BargainDTO bargainDTO){
        return Bargain.builder()
                .id(bargainDTO.getId())
                .title(bargainDTO.getTitle())
                .content(bargainDTO.getContent())
                .startDt(bargainDTO.getStartDt())
                .endDt(bargainDTO.getEndDt())
                .bargainFile(bargainDTO.getBargainFile())
                .build();
    }


}
