package com.lunchbox.lunchboxdonation.service.test;

import com.lunchbox.lunchboxdonation.entity.test.Test;
import com.lunchbox.lunchboxdonation.repository.test.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public Test write(String testContent) {
        Test test = new Test();
        test.setTestContent(testContent);
        return testRepository.save(test);
    }
}
