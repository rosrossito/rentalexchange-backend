package com.upteam.auth.repository;

import com.upteam.auth.domain.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

import javax.persistence.*;

import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Repository
@Transactional
public class SystemUserJpaRepository implements SystemUserRepository{

    @PersistenceContext
    @Autowired
    private EntityManager em;

    @Override
    public SystemUser create(SystemUser entity) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        em.persist(entity);
        return entity;
    }

    @Override
    public SystemUser getById(Long id) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return em.find(SystemUser.class, id);
    }

    @Override
    public SystemUser update(SystemUser entity) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        em.merge(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        SystemUser su = getById(id);
        if (su!=null) em.remove(su);
    }


    @Override
    public SystemUser searchByEmail(String eMail) {
        SystemUser result;
        TypedQuery<SystemUser> q = em.createNamedQuery("systemUser.findByMail", SystemUser.class);
        q.setParameter("email", eMail);
        try {
            result = q.getSingleResult();
        } catch (NoResultException e)
        {
            result= null;
        }
        return result;
    }
}
