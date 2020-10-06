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
public class GetActiveBankResponse implements Serializable {

    private String status;

    private GetActiveBank data;

    public GetActiveBank getData() {
        return data;
    }


    public String getStatus() {
        return status;
    }


    public void setData(GetActiveBank data) {
        this.data = data;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GetActiveBankResponse{" +
                "data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
