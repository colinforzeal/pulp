package net.petrikainulainen.spring.social.signinmvc.user.repository;

import net.petrikainulainen.spring.social.signinmvc.user.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PagesRepository extends JpaRepository<Page,Long> {

}
