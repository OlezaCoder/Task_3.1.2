package com.oleza.spring.springboot.task_312.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oleza.spring.springboot.task_312.model.User;
@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}
