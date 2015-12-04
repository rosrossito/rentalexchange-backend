package com.upteam.auth.repository;

/**
 * Created by opasichnyk on 12/4/2015.
 */
public interface BaseRepository<T, L> {
    T create (T entity);
    T getById(L id);
    T update(T entity);
    void delete(L id);
}
