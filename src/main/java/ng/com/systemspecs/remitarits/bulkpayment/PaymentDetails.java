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
public class PaymentDetails implements Serializable {

    private String amount;

    private String benficiaryAccountNumber;

    private String benficiaryBankCode;

    private String benficiaryEmail;

    private String narration;

    private String transRef;


    public String getAmount() {
        return amount;
    }


    public String getBenficiaryAccountNumber() {
        return benficiaryAccountNumber;
    }


    public String getBenficiaryBankCode() {
        return benficiaryBankCode;
    }


    public String getBenficiaryEmail() {
        return benficiaryEmail;
    }


    public String getNarration() {
        return narration;
    }


    public String getTransRef() {
        return transRef;
    }


    public void setAmount(String amount) {
        this.amount = amount;
    }


    public void setBenficiaryAccountNumber(String benficiaryAccountNumber) {
        this.benficiaryAccountNumber = benficiaryAccountNumber;
    }


    public void setBenficiaryBankCode(String benficiaryBankCode) {
        this.benficiaryBankCode = benficiaryBankCode;
    }


    public void setBenficiaryEmail(String benficiaryEmail) {
        this.benficiaryEmail = benficiaryEmail;
    }


    public void setNarration(String narration) {
        this.narration = narration;
    }


    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }


    @Override
    public String toString() {
        return "PaymentDetails{" +
                "amount='" + amount + '\'' +
                ", benficiaryAccountNumber='" + benficiaryAccountNumber + '\'' +
                ", benficiaryBankCode='" + benficiaryBankCode + '\'' +
                ", benficiaryEmail='" + benficiaryEmail + '\'' +
                ", narration='" + narration + '\'' +
                ", transRef='" + transRef + '\'' +
                '}';
    }
}
