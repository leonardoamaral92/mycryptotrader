package com.lmarques.mystocktrader.repository;

import com.lmarques.mystocktrader.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    public List<Portfolio> findByInvestorId(Long investorId);
}
