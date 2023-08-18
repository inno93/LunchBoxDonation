package com.lunchbox.lunchboxdonation.repository.lunchboxoption;

import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBoxOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchBoxOptionRepository extends JpaRepository<LunchBoxOption, Long>, LunchBoxOptionQueryDSL {
}
