package ru.martha.autotesting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.martha.autotesting.entity.Role;
import ru.martha.autotesting.entity.User;
import ru.martha.autotesting.repository.RolesRepository;
import ru.martha.autotesting.repository.UsersRepository;
import ru.martha.autotesting.service.AdministratorService;

import java.util.List;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void createOrUpdateUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public void removeUser(User user) {
        usersRepository.delete(user);
    }

    @Override
    public List<Role> getAllRoles() {
        return rolesRepository.findAll();
    }
}
