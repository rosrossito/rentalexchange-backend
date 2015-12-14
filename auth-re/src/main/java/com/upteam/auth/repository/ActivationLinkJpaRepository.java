package com.upteam.auth.repository;

import com.upteam.auth.domain.ActivationLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by olegls2000 on 12/4/2015.
 */


@Repository
@Transactional
public class ActivationLinkJpaRepository implements ActivationLinkRepository {

    @PersistenceContext
    @Autowired
    private EntityManager em;

    @Override
    public ActivationLink create(ActivationLink entity) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        em.persist(entity);
        return entity;
    }

    @Override
    public ActivationLink getById(Long id) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return em.find(ActivationLink.class, id);
    }

    @Override
    public void delete(Long id) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        ActivationLink al = getById(id);
        if (al!=null) em.remove(al);
    }

    @Override
    public ActivationLink update(ActivationLink entity) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        return em.merge(entity);
    }

    @Override
    public ActivationLink getLinkByUUID(String uuid) {
        //TODO REN-37 [BackEnd]Создание базового функционала по работе с БД >Maxim
        ActivationLink result;
        TypedQuery<ActivationLink> q = em.createNamedQuery("ActivationLink.findByUUID", ActivationLink.class);
        q.setParameter("uuid", uuid);
        try {
            result = q.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        return result;
    }

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
