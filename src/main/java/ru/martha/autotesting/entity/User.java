package ru.martha.autotesting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Setter
@Getter
public class User implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    private String role;
}
