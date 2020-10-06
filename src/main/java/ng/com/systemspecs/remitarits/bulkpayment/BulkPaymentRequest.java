package ng.com.systemspecs.remitarits.bulkpayment;

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
public class BulkPaymentRequest implements Serializable {

    private BulkPaymentInfo bulkPaymentInfo;

    private List<PaymentDetails> paymentDetails;


    public BulkPaymentInfo getBulkPaymentInfo() {
        return bulkPaymentInfo;
    }


    public List<PaymentDetails> getPaymentDetails() {
        return paymentDetails;
    }


    public void setBulkPaymentInfo(BulkPaymentInfo bulkPaymentInfo) {
        this.bulkPaymentInfo = bulkPaymentInfo;
    }


    public void setPaymentDetails(List<PaymentDetails> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    @Override
    public String toString() {
        return "BulkPaymentRequest{" +
                "bulkPaymentInfo=" + bulkPaymentInfo +
                ", paymentDetails=" + paymentDetails +
                '}';
    }
}
