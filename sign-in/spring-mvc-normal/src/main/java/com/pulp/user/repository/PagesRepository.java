package com.pulp.user.repository;

import com.pulp.user.model.Page;
import com.pulp.user.model.Site;
import javafx.collections.transformation.SortedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
public interface PagesRepository extends JpaRepository<Page,Long> {

   public List<Page> findBySite(Site site);
   public Page findBySiteAndIsMainPage(Site site,Boolean isMainPage);
   public Page findByName(String name);
   Page findBySiteAndName(Site site,String name);
   Page findOneBySite(Site site);
}


