package ng.com.systemspecs.remitarits.bulkpaymentstatus;

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
public class PaymentStatusBulk implements Serializable {

    private String batchRef;

    private BulkPaymentStatusInfo bulkPaymentStatusInfo;

    private String bulkRef;

    private List<BulkPaymentDetails> paymentDetails;


    public String getBatchRef() {
        return batchRef;
    }


    public BulkPaymentStatusInfo getBulkPaymentStatusInfo() {
        return bulkPaymentStatusInfo;
    }


    public String getBulkRef() {
        return bulkRef;
    }


    public List<BulkPaymentDetails> getPaymentDetails() {
        return paymentDetails;
    }


    public void setBatchRef(String batchRef) {
        this.batchRef = batchRef;
    }


    public void setBulkPaymentStatusInfo(BulkPaymentStatusInfo bulkPaymentStatusInfo) {
        this.bulkPaymentStatusInfo = bulkPaymentStatusInfo;
    }


    public void setBulkRef(String bulkRef) {
        this.bulkRef = bulkRef;
    }


    public void setPaymentDetails(List<BulkPaymentDetails> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    @Override
    public String toString() {
        return "PaymentStatusBulk{" +
                "batchRef='" + batchRef + '\'' +
                ", bulkPaymentStatusInfo=" + bulkPaymentStatusInfo +
                ", bulkRef='" + bulkRef + '\'' +
                ", paymentDetails=" + paymentDetails +
                '}';
    }
}
