package com.dng.cs.core.entity;

import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "CONTRACT")
@ToString
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
    private String conCat;
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
    private ContractEntity clientId;
    @Basic
    @Column(name = "CURR")
    private String curr;
    @Basic
    @Column(name = "AMOUNT_AVAILABLE")
    private Integer amountAvailable;
    @Basic
    @Column(name = "DATE_OPEN")
    private Date dateOpen;
    @Basic
    @Column(name = "DATE_EXPIRE")
    private Date dateExpire;
    @Basic
    @Column(name = "CARD_EXPIRE")
    private String cardExpire;
    @Basic
    @Column(name = "CONTR_STATUS")
    private String contrStatus;
    @Basic
    @Column(name = "ADD_INFO")
    private String addInfo;
    @Basic
    @Column(name = "IS_READY")
    private String isReady;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getConCat() {
        return conCat;
    }

    public void setConCat(String conCat) {
        this.conCat = conCat;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct;
    }

    public ContractEntity getClientId() {
        return clientId;
    }

    public void setClientId(ContractEntity clientId) {
        this.clientId = clientId;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        this.dateOpen = dateOpen;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getContrStatus() {
        return contrStatus;
    }

    public void setContrStatus(String contrStatus) {
        this.contrStatus = contrStatus;
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
        ContractEntity that = (ContractEntity) o;
        return id == that.id && Objects.equals(state, that.state) && Objects.equals(productCat, that.productCat) && Objects.equals(conCat, that.conCat) && Objects.equals(contractNumber, that.contractNumber) && Objects.equals(commentText, that.commentText) && Objects.equals(mainProduct, that.mainProduct) && Objects.equals(clientId, that.clientId) && Objects.equals(curr, that.curr) && Objects.equals(amountAvailable, that.amountAvailable) && Objects.equals(dateOpen, that.dateOpen) && Objects.equals(dateExpire, that.dateExpire) && Objects.equals(cardExpire, that.cardExpire) && Objects.equals(contrStatus, that.contrStatus) && Objects.equals(addInfo, that.addInfo) && Objects.equals(isReady, that.isReady);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, id, productCat, conCat, contractNumber, commentText, mainProduct, clientId, curr, amountAvailable, dateOpen, dateExpire, cardExpire, contrStatus, addInfo, isReady);
    }
}
