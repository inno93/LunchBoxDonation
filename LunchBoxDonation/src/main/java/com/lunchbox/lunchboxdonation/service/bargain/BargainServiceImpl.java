package com.lunchbox.lunchboxdonation.service.bargain;

import com.lunchbox.lunchboxdonation.domain.bargain.BargainDTO;
import com.lunchbox.lunchboxdonation.entity.bargain.Bargain;
import com.lunchbox.lunchboxdonation.entity.bargain.BargainSearch;
import com.lunchbox.lunchboxdonation.repository.bargain.BargainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BargainServiceImpl implements BargainService {
    private final BargainRepository bargainRepository;

    @Override
    public Long bargainInsert(BargainDTO bargainDTO) {
        return bargainRepository.save(toEntity(bargainDTO)).getId();
    }

    @Override
    public Page<BargainDTO> getBargainList(Pageable pageable, BargainSearch bargainSearch) {
        return bargainRepository.bargainList(pageable,bargainSearch);
    }

}
