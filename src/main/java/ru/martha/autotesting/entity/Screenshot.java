package ru.martha.autotesting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
public class Screenshot implements Serializable{
    @Id
    @GeneratedValue
    private String name;
    private boolean status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
