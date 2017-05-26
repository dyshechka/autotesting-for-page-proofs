package ru.martha.autotesting.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import ru.martha.autotesting.converter.RoleConverter;
import ru.martha.autotesting.entity.Role;
import ru.martha.autotesting.entity.User;
import ru.martha.autotesting.service.AdministratorService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "adminController")
@ViewScoped
public class AdministratorController implements Serializable {

    @ManagedProperty("#{administratorServiceImpl}")
    @Setter
    private AdministratorService service;

    @ManagedProperty("#{passwordEncoder}")
    @Setter
    private ShaPasswordEncoder encoder;

    @Getter
    private List<User> users;
    @Getter
    private List<Role> roles;
    @Getter
    private RoleConverter roleConverter;

    @Getter
    @Setter
    private User selectedUser;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String repeatPassword;
    @Getter
    @Setter
    private boolean isEditMode;
    @Getter
    @Setter
    private boolean isNewUser;

    @PostConstruct
    private void init() {
        users = service.getAllUsers();
        roles = service.getAllRoles();
        roleConverter = new RoleConverter(roles);
    }

    public void onRowSelect() {
        isNewUser = false;
        isEditMode = false;
    }

    public void saveUser() {
        try {
            if (isNewUser) {
                if (!password.equals(repeatPassword)) {
                    FacesMessage message = new FacesMessage("Пароли не совпадают");
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                selectedUser.setPassword(encoder.encodePassword(password, null));
            }
            service.createOrUpdateUser(selectedUser);
            users = service.getAllUsers();
            isEditMode = false;
            isNewUser = false;
        } catch (Exception e) {
            FacesMessage message = new FacesMessage("Произошла ошибка при сохранении");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void createUser() {
        selectedUser = new User();
        selectedUser.setLogin("Новый пользователь");
        users.add(selectedUser);
        isEditMode = true;
        isNewUser = true;
    }

    public void cancelCreate() {
        users = service.getAllUsers();
        selectedUser = null;
        isNewUser = false;
    }

    public void removeUser() {
        service.removeUser(selectedUser);
        users = service.getAllUsers();
        selectedUser = null;
    }
}
