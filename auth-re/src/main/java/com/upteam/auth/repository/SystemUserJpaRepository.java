package com.upteam.auth.repository;

import com.upteam.auth.domain.SystemUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Repository
public class SystemUserJpaRepository implements SystemUserRepository{

    @Override
    public SystemUser create(SystemUser entity) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return null;
    }

    @Override
    public SystemUser getById(Long id) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return null;
    }

    @Override
    public SystemUser update(SystemUser entity) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return null;
    }

    @Override
    public void delete(Long id) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
    }


    @Override
    public SystemUser searchByEmail(String eMail) {
        return null;
    }
}
