package ru.martha.autotesting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class ProjectVersion implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String versionNumber;
    @OneToMany
    private List<Screenshot> screenshots;
}
