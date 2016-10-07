package com.pulp.user.repository;

import com.pulp.user.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PagesRepository extends JpaRepository<Page,Long> {

}
