package ng.com.systemspecs.remitarits.bankenquiry;

import java.io.Serializable;


/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class Banks implements Serializable {

    private String bankAccronym;

    private String bankCode;

    private String bankName;

    private String type;


    public String getBankAccronym() {
        return bankAccronym;
    }


    public String getBankCode() {
        return bankCode;
    }


    public String getBankName() {
        return bankName;
    }


    public String getType() {
        return type;
    }


    public void setBankAccronym(String bankAccronym) {
        this.bankAccronym = bankAccronym;
    }


    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }


    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Banks{" +
                "bankAccronym='" + bankAccronym + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
