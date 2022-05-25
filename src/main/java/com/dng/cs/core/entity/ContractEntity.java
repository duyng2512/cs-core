package com.dng.cs.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "CONTRACT")
@ToString
@Getter @Setter
public class ContractEntity {
    @Basic
    @Column(name = "STATE")
    private String state;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "PRODUCT_CAT")
    private String productCat;
    @Basic
    @Column(name = "CON_CAT")
    private String contractCat;
    @Basic
    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;
    @Basic
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Basic
    @Column(name = "MAIN_PRODUCT")
    private String mainProduct;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT__ID")
    private ClientEntity clientId;
    @Basic
    @Column(name = "CURR")
    private String currency;
    @Basic
    @Column(name = "AMOUNT_AVAILABLE")
    private Long amountAvailable;
    @Basic
    @Column(name = "DATE_OPEN")
    private OffsetDateTime dateOpen;
    @Basic
    @Column(name = "DATE_EXPIRE")
    private OffsetDateTime dateExpire;
    @Basic
    @Column(name = "CARD_EXPIRE")
    private String cardExpire;
    @Basic
    @Column(name = "CONTR_STATUS")
    private String contractStatus;
    @Basic
    @Column(name = "ADD_INFO")
    private String addInfo;
    @Basic
    @Column(name = "IS_READY")
    private String isReady;

}
