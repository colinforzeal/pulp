package com.pulp.user.repository;

import com.pulp.user.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SitesRepository extends JpaRepository<Site,Long>{

}
