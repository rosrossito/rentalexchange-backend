package com.upteam.auth.repository;

import com.upteam.auth.domain.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by opasichnyk on 11/25/2015.
 */
@Repository
@Transactional
public class SystemUserJpaRepository implements SystemUserRepository{

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public SystemUser create(SystemUser entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public SystemUser getById(Long id) {
        return em.find(SystemUser.class, id);
    }

    @Override
    public SystemUser update(SystemUser entity) {
        em.merge(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
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
