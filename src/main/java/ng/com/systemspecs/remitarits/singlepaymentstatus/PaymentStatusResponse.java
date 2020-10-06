package ng.com.systemspecs.remitarits.singlepaymentstatus;

import java.io.Serializable;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class PaymentStatusResponse implements Serializable {

    private String status;

    private PaymentStatus data;

    public PaymentStatus getData() {
        return data;
    }


    public String getStatus() {
        return status;
    }


    public void setData(PaymentStatus data) {
        this.data = data;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}
