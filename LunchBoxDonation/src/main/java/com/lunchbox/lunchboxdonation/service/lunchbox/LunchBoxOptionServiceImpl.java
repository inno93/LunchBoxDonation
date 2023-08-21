package com.lunchbox.lunchboxdonation.service.lunchbox;

import com.lunchbox.lunchboxdonation.repository.lunchbox.LunchBoxRepository;
import com.lunchbox.lunchboxdonation.repository.lunchboxoption.LunchBoxOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LunchBoxOptionServiceImpl implements LunchBoxOptionService {
    private final LunchBoxRepository lunchBoxRepository;
    private final LunchBoxOptionRepository lunchBoxOptionRepository;
    @Override
    public void deleteOptionsById(Long id) {
        lunchBoxOptionRepository.deleteById(id);
    }
}
