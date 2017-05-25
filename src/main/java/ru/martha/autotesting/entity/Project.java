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
@Table(name = "PROJECTS")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @OneToMany
    @JoinColumn(name = "PROJECT_ID")
    private List<ProjectVersion> versions;

    @ManyToMany
    @JoinTable(
            name = "PROJECT_USER",
            joinColumns = @JoinColumn(
                    name = "PROJECT_ID",
                    referencedColumnName = "ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "USER_ID",
                    referencedColumnName = "ID"
            )
    )
    private List<User> users;

    @OneToMany
    @JoinColumn(name = "PROJECT_ID")
    private List<TestCase> testCases;
}
