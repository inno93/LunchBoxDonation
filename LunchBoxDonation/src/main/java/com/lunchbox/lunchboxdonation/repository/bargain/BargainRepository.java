package com.lunchbox.lunchboxdonation.repository.bargain;

import com.lunchbox.lunchboxdonation.entity.bargain.Bargain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BargainRepository extends JpaRepository<Bargain, Long>, BargainQueryDSL {
}
