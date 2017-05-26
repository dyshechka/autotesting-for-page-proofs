package ru.martha.autotesting.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.martha.autotesting.enums.RoleCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_ROLES")
@EqualsAndHashCode
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    @Setter
    private long id;

    @Column(name = "NAME", nullable = false, unique = true)
    @Getter
    @Setter
    private String name;

    @Column(name = "ROLE_CODE", nullable = false)
    @Getter
    @Setter
    private int code;

    @Transient
    private RoleCode roleCode;

    public void setRoleCode(RoleCode roleCode) {
        this.roleCode = roleCode;
        this.code = roleCode.getCode();
    }

    public RoleCode getRoleCode() {
        if (this.roleCode == null) {
            roleCode = RoleCode.getByCode(this.code);
        }
        return roleCode;
    }
}
