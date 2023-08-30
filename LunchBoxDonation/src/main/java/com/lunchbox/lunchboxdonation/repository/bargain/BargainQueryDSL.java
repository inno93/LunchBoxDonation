package com.lunchbox.lunchboxdonation.repository.bargain;


import com.lunchbox.lunchboxdonation.domain.bargain.BargainDTO;
import com.lunchbox.lunchboxdonation.entity.bargain.BargainSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BargainQueryDSL  {
    
    public Page<BargainDTO> bargainList(Pageable pageable, BargainSearch bargainSearch);
}
