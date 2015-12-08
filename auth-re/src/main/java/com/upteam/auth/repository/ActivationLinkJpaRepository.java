package com.upteam.auth.repository;

import com.upteam.auth.domain.ActivationLink;
import org.springframework.stereotype.Repository;

/**
 * Created by olegls2000 on 12/4/2015.
 */
@Repository
public class ActivationLinkJpaRepository implements ActivationLinkRepository {

    @Override
    public ActivationLink create(ActivationLink entity) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return null;
    }

    @Override
    public ActivationLink getById(Long id) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return null;
    }

    @Override
    public void delete(Long id) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
    }

    @Override
    public ActivationLink update(ActivationLink entity) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return null;
    }

    @Override
    public ActivationLink getLinkByUUID(String uuid) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return null;
    }


}
