package com.muhsin.watchtogether.repository;

import com.muhsin.watchtogether.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("user")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM d2q60ocmgllri7.public.user", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT * FROM d2q60ocmgllri7.public.user WHERE id = ?1", nativeQuery = true)
    User findUserById(Integer id);

}
