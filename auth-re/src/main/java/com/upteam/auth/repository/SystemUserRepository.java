package com.upteam.auth.repository;

import com.upteam.auth.domain.SystemUser;

/**
 * Created by opasichnyk on 11/25/2015.
 */
public interface SystemUserRepository extends BaseRepository<SystemUser, Long>{
    SystemUser searchByEmail(String eMail);
 }
