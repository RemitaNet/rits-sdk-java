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
public class PaymentStatusRequest implements Serializable {

    private String transRef;


    public String getTransRef() {
        return transRef;
    }


    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }
}
