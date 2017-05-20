package ru.martha.autotesting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
public class Action implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    private List<ActionArgument> arguments;
}
