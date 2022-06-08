package com.dng.cs.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;

@Entity
@Table(name = "ADDRESS")
@ToString
@Getter @Setter
public class AddressEntity {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT__ID")
    @ToString.Exclude
    private ClientEntity clientId;

    @Basic
    @Column(name = "ADDRESS_TYPE")
    private String addressType;
    @Basic
    @Column(name = "ADDRESS_LINE")
    private String addressLine;
    @Basic
    @Column(name = "ZIP_CODE")
    private String zipCode;
    @Basic
    @Column(name = "PHONE")
    private String phone;
    @Basic
    @Column(name = "E_MAIL")
    private String eMail;
    @Basic
    @Column(name = "URL")
    private String url;
    @Basic
    @Column(name = "DELIVERY_TYPE")
    private String deliveryType;
    @Basic
    @Column(name = "IS_READY")
    private String isReady;

}
