package com.dng.cs.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserEntity {
    @Basic
    @Column(name = "STATE")
    private String state;
    @Basic
    @Column(name = "LAST_LOGIN")
    private Timestamp lastLogin;
    @Basic
    @Column(name = "DATE_CREATED")
    private Timestamp dateCreated;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "USER_NAME")
    private String userName;
    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(state, that.state) && Objects.equals(lastLogin, that.lastLogin) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, lastLogin, dateCreated, id, userName, password);
    }
}
