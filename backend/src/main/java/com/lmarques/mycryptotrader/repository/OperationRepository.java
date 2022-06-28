package com.lmarques.mycryptotrader.repository;

import com.lmarques.mycryptotrader.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
