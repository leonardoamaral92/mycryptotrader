package com.lmarques.mycryptotrader.repository;

import com.lmarques.mycryptotrader.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
    public Optional<Investor> findByUserId(Long id);
}
