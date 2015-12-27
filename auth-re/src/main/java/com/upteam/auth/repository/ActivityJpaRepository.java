package com.upteam.auth.repository;

import com.upteam.auth.domain.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by vnikolaev on 27.12.2015.
 */
@Repository
@Transactional
public class ActivityJpaRepository implements ActivityRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Activity create(Activity entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Activity getById(Long id) {
        return em.find(Activity.class, id);
    }

    @Override
    public Activity update(Activity entity) {
        em.merge(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        Activity userActivity = getById(id);
        if (userActivity!=null) em.remove(userActivity);
    }
}
