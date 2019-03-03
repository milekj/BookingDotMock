package com.milekj.dbtest.repository;

import com.milekj.dbtest.entity.DbUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbUserDetailsRepository extends JpaRepository<DbUserDetails, Long> {
    DbUserDetails getByUserUsername(String username);
}
