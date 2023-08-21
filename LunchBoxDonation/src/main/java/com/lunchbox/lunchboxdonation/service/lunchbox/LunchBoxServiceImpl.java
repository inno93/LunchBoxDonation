package com.lunchbox.lunchboxdonation.service.lunchbox;


import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxOption;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxSearch;
import com.lunchbox.lunchboxdonation.repository.lunchbox.LunchBoxRepository;
import com.lunchbox.lunchboxdonation.repository.lunchboxoption.LunchBoxOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LunchBoxServiceImpl implements LunchBoxService {
    private final LunchBoxRepository lunchBoxRepository;
    private final LunchBoxOptionRepository lunchBoxOptionRepository;


    @Override
    public Long lunchBoxInsert(LunchBoxDTO lunchBoxDTO) {
        return lunchBoxRepository.save(toEntity(lunchBoxDTO)).getId();
    }

    @Override
    public Page<LunchBoxDTO> lunchBoxList(Pageable pageable, LunchBoxSearch lunchBoxSearch) {
        return lunchBoxRepository.lunchBoxList(pageable,lunchBoxSearch);
    }

    @Override
    public Long LunchBoxUpdate(LunchBoxDTO lunchBoxDTO) {
        Optional<LunchBox> lunchBoxData = lunchBoxRepository.findById(lunchBoxDTO.getId());
        if(lunchBoxData.isPresent()){
            LunchBox lunchbox = lunchBoxData.get();
            lunchbox.setLunchboxTitle(lunchBoxDTO.getLunchboxTitle());
            lunchbox.setLunchboxThumbNailingIMG(lunchBoxDTO.getLunchboxThumbNailingIMG());
            lunchbox.setPrice(lunchBoxDTO.getPrice());
            if(lunchBoxDTO.getLunchBoxOptions().size() > 0){
                int i = 0;
                for(LunchBoxOption option : lunchBoxDTO.getLunchBoxOptions()){
                    //기존 DB에 값이 저장되어 있는 경우 -> update
                    if(option.getId() != null){
                        LunchBoxOption existingOption = lunchBoxOptionRepository.findById(option.getId()).orElse(null);
                        if (existingOption != null) {
                            existingOption.setLunchOptionTitle(option.getLunchOptionTitle());
                            existingOption.setLunchOptionPrice(option.getLunchOptionPrice());
                        }
                    }else{
                        // ID가 null인 경우, 새로운 엔티티로 간주하여 저장
                        option.setLunchOptionTitle(lunchBoxDTO.getLunchBoxOptions().get(i).getLunchOptionTitle());
                        option.setLunchOptionPrice(lunchBoxDTO.getLunchBoxOptions().get(i).getLunchOptionPrice());
                        option.setLunchbox(lunchbox);
                        lunchBoxOptionRepository.save(option);
                    }
                    i++;
                }
            }
        }
        return lunchBoxDTO.getId();
    }

    @Override
    public LunchBox getLunchBoxWithOptionByLunchBoxId(Long id) {

        return lunchBoxRepository.lunchBoxDetail(id);
    }

    @Override
    public void deleteLunchBoxAndOptionsByLunchBoxId(Long id) {
        lunchBoxRepository.deleteById(id);
    }

    @Override
    public String getImgName(Long id) {
        return lunchBoxRepository.getImgName(id);
    }
}
