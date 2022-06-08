package com.dng.cs.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PLASTIC")
@Getter @Setter
@ToString
public class PlasticEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CONTRACT__OID")
    private ContractEntity contractId;
    @Basic
    @Column(name = "CARD_SUBTYPE")
    private String cardSubtype;
    @Basic
    @Column(name = "CARD_NUMBER")
    private String cardNumber;
    @Basic
    @Column(name = "CARD_EXPIRE")
    private String cardExpire;
    @Basic
    @Column(name = "PRODUCTION_EVENT")
    private String productionEvent;
    @Basic
    @Column(name = "PIN_BLOCK")
    private String pinBlock;
    @Basic
    @Column(name = "PVV")
    private String pvv;
    @Basic
    @Column(name = "CVC")
    private String cvc;
    @Basic
    @Column(name = "CVC2")
    private String cvc2;
}
