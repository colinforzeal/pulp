package com.pulp.user.repository;

import com.pulp.user.model.Page;
import javafx.collections.transformation.SortedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface PagesRepository extends JpaRepository<Page,Long> {
   public  SortedList<Page> findBySiteOrderByPageidDesc(Long id);
}
