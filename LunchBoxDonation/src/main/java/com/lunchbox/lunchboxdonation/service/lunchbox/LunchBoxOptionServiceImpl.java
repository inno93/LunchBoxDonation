package com.lunchbox.lunchboxdonation.service.lunchbox;

import com.lunchbox.lunchboxdonation.repository.lunchbox.LunchBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LunchBoxOptionServiceImpl implements LunchBoxOptionService {
    private final LunchBoxRepository lunchBoxRepository;
    @Override
    public void deleteOptionsById(Long id) {
        lunchBoxRepository.deleteById(id);
    }
}
