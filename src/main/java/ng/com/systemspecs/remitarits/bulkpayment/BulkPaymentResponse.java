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
public class BulkPaymentResponse implements Serializable {

    private String status;

    private BulkPayment data;

    public BulkPayment getData() {
        return data;
    }


    public String getStatus() {
        return status;
    }


    public void setData(BulkPayment data) {
        this.data = data;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BulkPaymentResponse{" +
                "data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
