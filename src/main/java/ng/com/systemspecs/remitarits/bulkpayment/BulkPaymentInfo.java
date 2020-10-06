package ng.com.systemspecs.remitarits.bulkpayment;

import java.io.Serializable;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class BulkPaymentInfo implements Serializable {

    private String bankCode;

    private String batchRef;

    private String debitAccount;

    private String narration;

    private String totalAmount;

    private String remitaFunded;

    private String generateRrrOnly;


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


    public String getTotalAmount() {
        return totalAmount;
    }

    public String getRemitaFunded() {
        return remitaFunded;
    }

    public String getGenerateRrrOnly() {
        return generateRrrOnly;
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


    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setRemitaFunded(String remitaFunded) {
        this.remitaFunded = remitaFunded;
    }

    public void setGenerateRrrOnly(String generateRrrOnly) {
        this.generateRrrOnly = generateRrrOnly;
    }

    @Override
    public String toString() {
        return "BulkPaymentInfo{" +
                "bankCode='" + bankCode + '\'' +
                ", batchRef='" + batchRef + '\'' +
                ", debitAccount='" + debitAccount + '\'' +
                ", narration='" + narration + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", remitaFunded='" + remitaFunded + '\'' +
                ", generateRrrOnly='" + generateRrrOnly + '\'' +
                '}';
    }
}
