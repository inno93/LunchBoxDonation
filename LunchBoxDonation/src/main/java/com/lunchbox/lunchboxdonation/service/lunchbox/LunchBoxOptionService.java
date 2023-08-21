package com.lunchbox.lunchboxdonation.service.lunchbox;

import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxOption;

public interface LunchBoxOptionService {

    //삭제
    public void deleteOptionsById(Long id);

}
