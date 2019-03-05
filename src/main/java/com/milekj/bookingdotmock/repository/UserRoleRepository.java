package com.milekj.bookingdotmock.repository;

import com.milekj.bookingdotmock.entity.UserRole;
import com.milekj.bookingdotmock.entity.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
}
