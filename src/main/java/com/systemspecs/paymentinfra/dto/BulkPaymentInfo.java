package com.systemspecs.paymentinfra.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class BulkPaymentInfo implements Serializable {

    private String bankCode;

    private String batchRef;

    private String debitAccount;

    private String narration;

    private BigDecimal totalAmount;


    public String getBankCode() {
        return bankCode;
    }


    public String getBatchRef() {
        return batchRef;
    }


    public String getDebitAccount() {
        return debitAccount;
    }


    public String getNarration() {
        return narration;
    }


    public BigDecimal getTotalAmount() {
        return totalAmount;
    }


    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }


    public void setBatchRef(String batchRef) {
        this.batchRef = batchRef;
    }


    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }


    public void setNarration(String narration) {
        this.narration = narration;
    }


    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
