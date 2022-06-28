package com.lmarques.mycryptotrader.repository;

import com.lmarques.mycryptotrader.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    public List<Portfolio> findByInvestorId(Long investorId);
}
