package com.dng.cs.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
@ToString @Getter @Setter
public class ClientEntity {

    @Basic
    @Column(name = "STATE")
    private String state;
    @Basic
    @Column(name = "DATE_CREATED")
    private Instant dateCreated = Instant.now();
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "BRANCH")
    private String branch;
    @Basic
    @Column(name = "CLIENT_CAT")
    private String clientCat;
    @Basic
    @Column(name = "PRODUCT_CAT")
    private String productCat;
    @Basic
    @Column(name = "CLIENT_NAME")
    private String clientName;
    @Basic
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Basic
    @Column(name = "GENDER")
    private String gender;
    @Basic
    @Column(name = "CLIENT_NUMBER")
    private String clientNumber;
    @Basic
    @Column(name = "REG_NUMBER")
    private String regNumber;
    @Basic
    @Column(name = "ADDRESS_LINE")
    private String addressLine;
    @Basic
    @Column(name = "PHONE")
    private String phone;
    @Basic
    @Column(name = "FAX")
    private String fax;
    @Basic
    @Column(name = "E_MAIL")
    private String eMail;
    @Basic
    @Column(name = "ADD_DATE")
    private Date addDate;
    @Basic
    @Column(name = "ADD_INFO")
    private String addInfo;
    @Basic
    @Column(name = "IS_READY")
    private String isReady;

    @OneToMany(orphanRemoval = true, mappedBy = "clientId", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<ContractEntity> contracts = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "clientId", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<AddressEntity> addresses = new ArrayList<>();
}
