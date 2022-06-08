package com.dng.cs.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "ROLE")
@Getter @Setter
public class RoleEntity {
    @Basic
    @Column(name = "STATE")
    private String state;
    @Basic
    @Column(name = "DATE_CREATED")
    private Timestamp dateCreated = Timestamp.from(Instant.now());
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "USER_ID")
    private Integer userId;

    @Basic
    @Column(name = "ROLE_NAME")
    private String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id && Objects.equals(state, that.state) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(userId, that.userId) && Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, dateCreated, id, userId, roleName);
    }
}
