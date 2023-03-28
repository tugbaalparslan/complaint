package com.example.complaint.dao;

import com.example.complaint.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByPhoneNumber(String phoneNumber); //plugin the <entity type, primary key>
    /* that's it ... methods that JPARepository provides are accessed such as:
     *           findAll()
     *           findByID(..)
     *           save(..)
     *           deleteById(..)
     *           etc.
     * */
}
