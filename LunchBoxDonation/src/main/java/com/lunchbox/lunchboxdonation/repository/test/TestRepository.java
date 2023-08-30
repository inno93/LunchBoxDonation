package com.lunchbox.lunchboxdonation.repository.test;

import com.lunchbox.lunchboxdonation.entity.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test , Long> {
}
