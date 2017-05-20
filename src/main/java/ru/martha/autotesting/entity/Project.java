package ru.martha.autotesting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Project implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @OneToMany
    private List<ProjectVersion> versions;
    @OneToMany
    private List<User> users;
    @OneToMany
    private List<TestCase> testCases;
}
