package dmitriy.blanutsa.coursework.persistance.entities;

import dmitriy.blanutsa.coursework.security.RoleConstants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleConstants name;
}
