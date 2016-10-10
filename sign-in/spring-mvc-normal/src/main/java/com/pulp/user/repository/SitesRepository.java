package com.pulp.user.repository;


import com.pulp.user.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface SitesRepository extends JpaRepository<Site,Long>{
    public Site findOneByName(String name);
    public Site findByName(String name);
    public List<Site> findAll();
    @Transactional
    public Long deleteByName(String name);
}
