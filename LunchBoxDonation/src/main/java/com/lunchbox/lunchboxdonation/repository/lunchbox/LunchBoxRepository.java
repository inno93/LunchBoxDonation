package com.lunchbox.lunchboxdonation.repository.lunchbox;

import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchBoxRepository extends JpaRepository<LunchBox, Long>, LunchBoxQueryDSL {
}
