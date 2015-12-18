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
 * Created by opasechnik on 12/4/2015.
 */
@Repository
@Transactional
public class ActivationLinkJpaRepository implements ActivationLinkRepository {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public ActivationLink create(ActivationLink entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public ActivationLink getById(Long id) {
        return em.find(ActivationLink.class, id);
    }

    @Override
    public void delete(Long id) {
        ActivationLink al = getById(id);
        if (al!=null) em.remove(al);
    }

    @Override
    public ActivationLink update(ActivationLink entity) {
        return em.merge(entity);
    }

    @Override
    public ActivationLink getLinkByUUID(String uuid) {
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

}
