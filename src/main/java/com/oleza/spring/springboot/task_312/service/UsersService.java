package com.oleza.spring.springboot.task_312.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oleza.spring.springboot.task_312.repositories.UsersRepository;
import com.oleza.spring.springboot.task_312.model.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {

        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {

        return usersRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> user = usersRepository.findById(id);

        return user.orElse(null);
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }
}
