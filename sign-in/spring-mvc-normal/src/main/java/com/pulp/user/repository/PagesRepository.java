package com.pulp.user.repository;

import com.pulp.user.model.Page;
import com.pulp.user.model.Site;
import javafx.collections.transformation.SortedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Set;


@Repository
public interface PagesRepository extends JpaRepository<Page,Long> {

   public ArrayList<Page> findBySite(Site site);

}


