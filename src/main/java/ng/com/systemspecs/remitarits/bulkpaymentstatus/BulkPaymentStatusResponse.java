package ng.com.systemspecs.remitarits.bulkpaymentstatus;

import java.io.Serializable;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class BulkPaymentStatusResponse implements Serializable {

    private String status;

    private PaymentStatusBulk data;

    public PaymentStatusBulk getData() {
        return data;
    }


    public String getStatus() {
        return status;
    }


    public void setData(PaymentStatusBulk data) {
        this.data = data;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BulkPaymentStatusResponse{" +
                "data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
