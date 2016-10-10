package com.pulp.user.repository;

import com.pulp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
    public User findById(Long id);

}
