package ng.com.systemspecs.remitarits.bankenquiry;

import java.io.Serializable;
import java.util.List;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class GetActiveBank implements Serializable {

    private List<Banks> banks;

    private String responseCode;

    private String responseDescription;

    private String responseId;


    public List<Banks> getBanks() {
        return banks;
    }


    public String getResponseCode() {
        return responseCode;
    }


    public String getResponseDescription() {
        return responseDescription;
    }


    public String getResponseId() {
        return responseId;
    }


    public void setBanks(List<Banks> banks) {
        this.banks = banks;
    }


    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }


    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }


    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    @Override
    public String toString() {
        return "GetActiveBank{" +
                "banks=" + banks +
                ", responseCode='" + responseCode + '\'' +
                ", responseDescription='" + responseDescription + '\'' +
                ", responseId='" + responseId + '\'' +
                '}';
    }
}
