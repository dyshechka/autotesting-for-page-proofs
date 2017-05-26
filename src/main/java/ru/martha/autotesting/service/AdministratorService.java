package ru.martha.autotesting.service;

import ru.martha.autotesting.entity.Role;
import ru.martha.autotesting.entity.User;

import java.util.List;

public interface AdministratorService {
    List<User> getAllUsers();
    void createOrUpdateUser(User user);
    void removeUser(User user);
    List<Role> getAllRoles();
}
