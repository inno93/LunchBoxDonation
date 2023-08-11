package com.lunchbox.lunchboxdonation.lunchbox;


import com.lunchbox.lunchboxdonation.domain.lunchbox.LunchBoxDTO;
import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.repository.lunchbox.LunchBoxRepository;
import com.lunchbox.lunchboxdonation.service.lunchbox.LunchBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class LunchBoxTests {

    @Autowired
    private  LunchBoxRepository lunchBoxRepository;



    @Test
    public void saveTests(){
        LunchBox lunchBox = new LunchBox();


        lunchBox.setLunchboxTitle("테스트");
        lunchBox.setLunchboxThumbNailingIMG("as.jpg");
        lunchBox.setPrice(3000);

        lunchBoxRepository.save(lunchBox);

    }
}
