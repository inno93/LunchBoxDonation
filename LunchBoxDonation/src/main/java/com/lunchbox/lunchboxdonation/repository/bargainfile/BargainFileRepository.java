package com.lunchbox.lunchboxdonation.repository.bargainfile;


import com.lunchbox.lunchboxdonation.entity.bargain.BargainFile;
import com.lunchbox.lunchboxdonation.repository.bargain.BargainQueryDSL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BargainFileRepository extends JpaRepository<BargainFile, Long>, BargainQueryDSL {
}
