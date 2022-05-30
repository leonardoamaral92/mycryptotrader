package com.lmarques.mystocktrader.repository;

import com.lmarques.mystocktrader.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
