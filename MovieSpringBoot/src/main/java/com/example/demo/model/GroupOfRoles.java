package com.example.demo.model;

import com.example.demo.model.Key.GroupOfRolesKey;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_of_roles")
public class GroupOfRoles {
    @EmbeddedId
    private GroupOfRolesKey id = new GroupOfRolesKey();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Exclude
    private Account account = new Account();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    @EqualsAndHashCode.Exclude
    private AccountRole accountRole = new AccountRole();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupOfRoles that = (GroupOfRoles) o;
        return id != null && Objects.equals(id, that.id);
    }

}
