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
public class SinglePaymentResponse implements Serializable {

    private String status;

    private SinglePayment data;

    public SinglePayment getData() {
        return data;
    }


    public String getStatus() {
        return status;
    }


    public void setData(SinglePayment data) {
        this.data = data;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SinglePaymentResponse{" +
                "data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
