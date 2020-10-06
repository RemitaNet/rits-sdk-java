package ng.com.systemspecs.remitarits.accountenquiry;

import java.io.Serializable;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class AccountEnqiryRequest implements Serializable {

    private String accountNo;

    private String bankCode;

    public String getAccountNo() {
        return accountNo;
    }


    public String getBankCode() {
        return bankCode;
    }


    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }


    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public String toString() {
        return "AccountEnqiryRequest{" +
                "accountNo='" + accountNo + '\'' +
                ", bankCode='" + bankCode + '\'' +
                '}';
    }
}
