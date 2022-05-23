package com.dng.cs.core.entity;

import jakarta.validation.constraints.Size;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CLIENT")
@ToString
public class ClientEntity {
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

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ContractEntity> contracts = new ArrayList<>();

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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getClientCat() {
        return clientCat;
    }

    public void setClientCat(String clientCat) {
        this.clientCat = clientCat;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
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
        ClientEntity that = (ClientEntity) o;
        return id == that.id && Objects.equals(state, that.state) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(branch, that.branch) && Objects.equals(clientCat, that.clientCat) && Objects.equals(productCat, that.productCat) && Objects.equals(clientName, that.clientName) && Objects.equals(birthDate, that.birthDate) && Objects.equals(gender, that.gender) && Objects.equals(clientNumber, that.clientNumber) && Objects.equals(regNumber, that.regNumber) && Objects.equals(addressLine, that.addressLine) && Objects.equals(phone, that.phone) && Objects.equals(fax, that.fax) && Objects.equals(eMail, that.eMail) && Objects.equals(addDate, that.addDate) && Objects.equals(addInfo, that.addInfo) && Objects.equals(isReady, that.isReady);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, dateCreated, id, branch, clientCat, productCat, clientName, birthDate, gender, clientNumber, regNumber, addressLine, phone, fax, eMail, addDate, addInfo, isReady);
    }
}
