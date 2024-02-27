package com.digitopia.digitopiacasestudy.repository;

import com.digitopia.digitopiacasestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByFullName(String fullName);

    Optional<User> findByEmail(String email);;

    Optional<User> deleteByEmail(String email);
}
