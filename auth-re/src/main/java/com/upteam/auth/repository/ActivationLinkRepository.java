package com.upteam.auth.repository;

import com.upteam.auth.domain.ActivationLink;

/**
 * Created by opasichnyk on 12/4/2015.
 */
public interface ActivationLinkRepository extends BaseRepository<ActivationLink, Long> {

    ActivationLink getLinkByUUID(String uuid);

}
