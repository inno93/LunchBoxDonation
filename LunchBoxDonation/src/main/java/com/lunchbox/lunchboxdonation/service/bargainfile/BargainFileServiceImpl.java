package com.lunchbox.lunchboxdonation.service.bargainfile;

import com.lunchbox.lunchboxdonation.entity.bargain.BargainFile;
import com.lunchbox.lunchboxdonation.repository.bargainfile.BargainFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BargainFileServiceImpl implements BargainFileService {
    private final BargainFileRepository bargainFileRepository;
    @Override
    public void bfInsert(BargainFile bargainFile) {
        bargainFileRepository.save(bargainFile);
    }
}
