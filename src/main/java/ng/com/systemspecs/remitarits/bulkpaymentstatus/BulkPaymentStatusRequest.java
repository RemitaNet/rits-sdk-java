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
public class BulkPaymentStatusRequest implements Serializable {

    private String batchRef;


    public String getBatchRef() {
        return batchRef;
    }


    public void setBatchRef(String batchRef) {
        this.batchRef = batchRef;
    }

    @Override
    public String toString() {
        return "BulkPaymentStatusRequest{" +
                "batchRef='" + batchRef + '\'' +
                '}';
    }
}
