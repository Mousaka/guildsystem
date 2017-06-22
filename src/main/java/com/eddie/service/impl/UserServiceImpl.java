package com.eddie.service.impl;

import com.eddie.builder.UserBuilder;
import com.eddie.exception.GuildSystemException;
import com.eddie.exception.UserException;
import com.eddie.model.User;
import com.eddie.model.enums.Role;
import com.eddie.repository.AbstractUserRepository;
import com.eddie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    AbstractUserRepository repository;

    @Autowired
    public UserServiceImpl(AbstractUserRepository repository){
        this.repository = repository;
    }

    @Override
    public User add(User user) {
        return repository.save(user);
    }

    @Override
    public User edit(User user) {
        return repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public User findOneByEmail(String email) throws GuildSystemException {
        return repository.findOneByEmail(email);
    }

}

