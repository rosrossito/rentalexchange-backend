package com.upteam.auth.repository;

import com.upteam.auth.domain.ActivationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;

/**
 * Created by opasichnyk on 12/4/2015.
 */
@Repository
public interface ActivationLinkRepository extends JpaRepository<ActivationLink, Long> {

    @Query("select c from ActivationLink c where c.systemuserId = :systemuser_id")
    ActivationLink getLinkBySystemUserID(@Param("systemuser_id") Long systemuser_id);

    @Query("select u from ActivationLink u where u.uuid = :uuid")
    ActivationLink getLinkByUUID(@Param("uuid")String uuid);
}
