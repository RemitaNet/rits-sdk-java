package ng.com.systemspecs.remitarits.service;

import ng.com.systemspecs.remitarits.accountenquiry.AccountEnqiryRequest;
import ng.com.systemspecs.remitarits.accountenquiry.AccountEnquiryResponse;
import ng.com.systemspecs.remitarits.bankenquiry.GetActiveBankResponse;
import ng.com.systemspecs.remitarits.bulkpayment.BulkPaymentRequest;
import ng.com.systemspecs.remitarits.bulkpayment.BulkPaymentResponse;
import ng.com.systemspecs.remitarits.bulkpaymentstatus.BulkPaymentStatusRequest;
import ng.com.systemspecs.remitarits.bulkpaymentstatus.BulkPaymentStatusResponse;
import ng.com.systemspecs.remitarits.singlepayment.SinglePaymentRequest;
import ng.com.systemspecs.remitarits.singlepayment.SinglePaymentResponse;
import ng.com.systemspecs.remitarits.singlepaymentstatus.PaymentStatusRequest;
import ng.com.systemspecs.remitarits.singlepaymentstatus.PaymentStatusResponse;

/**
 * The <tt>RemitaRITS</tt> interface provides six methods for integrating with Remita
 * RITS Gateway.
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public interface RemitaRITSService {

    /**
     * The bank Enquiry method retrieves the full list of
     * banks that can receive funds via the Single and Bulk
     * Payments Request method
     *
     */
    GetActiveBankResponse getActiveBanks();

    /**
     * This method retrieves the details of a designated account number
     *
     * @param accountEnqiryRequest request for which details should be returned
     *
     */
    AccountEnquiryResponse accountEnquiry(AccountEnqiryRequest accountEnqiryRequest);

    /**
     * This charges/debits a merchant’s account with a specified amount to
     * credit a designated beneficiary account.
     *
     * @param singlePaymentRequest request for which the single payment should be made
     *
     */
    SinglePaymentResponse singlePayment(SinglePaymentRequest singlePaymentRequest);

    /**
     * retrieves the status of a previous single payment request
     *
     * @param paymentStatusRequest request for which the single payment transaction details should be returned
     *
     */
    PaymentStatusResponse singlePaymentStatus(PaymentStatusRequest paymentStatusRequest);

    /**
     * A single amount is debited to credit multiple accounts across several banks.
     *
     * @param bulkPaymentRequest request for which the bulk payment should be made
     *
     */
    BulkPaymentResponse bulkPayment(BulkPaymentRequest bulkPaymentRequest);

    /**
     * retrieves the status of a previous bulk payment request
     *
     * @param bulkPaymentStatusRequest request for which the bulk payment transaction details should be returned
     *
     */
    BulkPaymentStatusResponse bulkPaymentStatus(BulkPaymentStatusRequest bulkPaymentStatusRequest);
}
