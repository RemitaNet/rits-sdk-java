package ng.com.systemspecs.remitarits.accountenquiry;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class AccountEnquiryResponse {

    private String status;

    private AccountEnquiry data;

    public AccountEnquiry getData() {
        return data;
    }


    public String getStatus() {
        return status;
    }


    public void setData(AccountEnquiry data) {
        this.data = data;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountEnquiryResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
