package ng.com.systemspecs.remitarits.bulkpayment;

import ng.com.systemspecs.remitarits.util.Data;

import java.io.Serializable;


/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class BulkPayment implements Serializable {

    private String authorizationId;

    private Data data;

    private String paymentDate;

    private String responseCode;

    private String responseDescription;

    private String responseId;

    private String rrr;

    private String transRef;


    public String getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    @Override
    public String toString() {
        return "BulkPayment{" +
                "authorizationId='" + authorizationId + '\'' +
                ", data='" + data + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseDescription='" + responseDescription + '\'' +
                ", responseId='" + responseId + '\'' +
                ", rrr='" + rrr + '\'' +
                ", transRef='" + transRef + '\'' +
                '}';
    }

}
