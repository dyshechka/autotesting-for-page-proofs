package ru.martha.autotesting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "PROJECT_VERSIONS")
public class ProjectVersion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "VERSION_NUMBER", nullable = false, unique = true)
    private String versionNumber;

    @OneToMany
    @JoinColumn(name = "PROJECT_VERSION_ID")
    private List<Screenshot> screenshots;
}
