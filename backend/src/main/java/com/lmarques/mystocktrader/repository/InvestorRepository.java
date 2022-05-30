package com.lmarques.mystocktrader.repository;

import com.lmarques.mystocktrader.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
    public Optional<Investor> findByUserId(Long id);
}
