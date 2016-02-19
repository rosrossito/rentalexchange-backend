package com.upteam.auth.test.repository;

import com.upteam.auth.domain.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    @Query("select u from SystemUser u where u.email = :email")
    SystemUser searchByEmail(@Param("email") String eMail);

}
