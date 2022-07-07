package com.lmarques.mycryptotrader.repository;

import com.lmarques.mycryptotrader.model.authentication.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByDescription(String description);
}
