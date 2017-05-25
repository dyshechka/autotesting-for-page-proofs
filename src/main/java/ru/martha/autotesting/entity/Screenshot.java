package ru.martha.autotesting.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "SCREENSHOTS")
public class Screenshot implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private  long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "STATUS")
    private boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;
}
