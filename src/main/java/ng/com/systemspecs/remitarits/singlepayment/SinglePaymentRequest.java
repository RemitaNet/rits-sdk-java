package ng.com.systemspecs.remitarits.singlepayment;

import java.io.Serializable;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class SinglePaymentRequest implements Serializable {

    private String amount;

    private String beneficiaryEmail;

    private String creditAccount;

    private String debitAccount;

    private String fromBank;

    private String narration;

    private String toBank;

    private String transRef;

    private String remitaFunded;

    private String generateRrrOnly;

    private String debitOnly;


    public String getAmount() {
        return amount;
    }


    public String getBeneficiaryEmail() {
        return beneficiaryEmail;
    }


    public String getCreditAccount() {
        return creditAccount;
    }


    public String getDebitAccount() {
        return debitAccount;
    }


    public String getFromBank() {
        return fromBank;
    }


    public String getNarration() {
        return narration;
    }


    public String getToBank() {
        return toBank;
    }


    public String getTransRef() {
        return transRef;
    }

    public String getRemitaFunded() {
        return remitaFunded;
    }

    public String getGenerateRrrOnly() {
        return generateRrrOnly;
    }


    public void setAmount(String amount) {
        this.amount = amount;
    }


    public void setBeneficiaryEmail(String beneficiaryEmail) {
        this.beneficiaryEmail = beneficiaryEmail;
    }


    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }


    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }


    public void setFromBank(String fromBank) {
        this.fromBank = fromBank;
    }


    public void setNarration(String narration) {
        this.narration = narration;
    }


    public void setToBank(String toBank) {
        this.toBank = toBank;
    }


    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public void setRemitaFunded(String remitaFunded) {
        this.remitaFunded = remitaFunded;
    }

    public void setGenerateRrrOnly(String generateRrrOnly) {
        this.generateRrrOnly = generateRrrOnly;
    }

    public String getDebitOnly() {
        return debitOnly;
    }

    public void setDebitOnly(String debitOnly) {
        this.debitOnly = debitOnly;
    }

    @Override
    public String toString() {
        return "SinglePaymentRequest{" +
                "amount='" + amount + '\'' +
                ", beneficiaryEmail='" + beneficiaryEmail + '\'' +
                ", creditAccount='" + creditAccount + '\'' +
                ", debitAccount='" + debitAccount + '\'' +
                ", fromBank='" + fromBank + '\'' +
                ", narration='" + narration + '\'' +
                ", toBank='" + toBank + '\'' +
                ", transRef='" + transRef + '\'' +
                ", remitaFunded='" + remitaFunded + '\'' +
                ", generateRrrOnly='" + generateRrrOnly + '\'' +
                ", debitOnly='" + debitOnly + '\'' +
                '}';
    }
}
