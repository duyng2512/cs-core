package com.dng.cs.core.entity;

import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ADDRESS")
@ToString
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
    @Basic
    @Column(name = "CLIENT__ID")
    private Integer clientId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getIsReady() {
        return isReady;
    }

    public void setIsReady(String isReady) {
        this.isReady = isReady;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return id == that.id && Objects.equals(state, that.state) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(clientId, that.clientId) && Objects.equals(addressType, that.addressType) && Objects.equals(addressLine, that.addressLine) && Objects.equals(zipCode, that.zipCode) && Objects.equals(phone, that.phone) && Objects.equals(eMail, that.eMail) && Objects.equals(url, that.url) && Objects.equals(deliveryType, that.deliveryType) && Objects.equals(isReady, that.isReady);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, dateCreated, id, clientId, addressType, addressLine, zipCode, phone, eMail, url, deliveryType, isReady);
    }
}
