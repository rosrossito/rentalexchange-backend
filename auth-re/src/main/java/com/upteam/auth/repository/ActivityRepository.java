package com.upteam.auth.repository;

import com.upteam.auth.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by me on 27.12.2015.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
