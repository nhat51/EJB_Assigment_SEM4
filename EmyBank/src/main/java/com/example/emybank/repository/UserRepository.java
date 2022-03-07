package com.example.emybank.repository;

import com.example.emybank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(Integer integer);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Transactional
    @Query("select u from User u where u.id = ?1")
    Optional<User> getUserForUpdate(Integer id);
}
