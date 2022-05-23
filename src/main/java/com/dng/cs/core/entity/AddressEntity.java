package com.dng.cs.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ADDRESS")
@ToString
@Getter @Setter
@EqualsAndHashCode
public class AddressEntity {
    @Basic
    @Column(name = "STATE")
    private String state;
    @Basic
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT__ID")
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
