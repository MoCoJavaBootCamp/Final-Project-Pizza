package com.example.demo.repository;

import com.example.demo.tables.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);

    @Query("select u from User u where u.username like concat('%',:email,'%')")
    User findUserByEmail(String email);
}
