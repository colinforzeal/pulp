package net.petrikainulainen.spring.social.signinmvc.user.repository;

import net.petrikainulainen.spring.social.signinmvc.user.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SitesRepository extends JpaRepository<Site,Long>{

}
