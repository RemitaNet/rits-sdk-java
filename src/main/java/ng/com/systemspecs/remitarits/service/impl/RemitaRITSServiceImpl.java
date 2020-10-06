package ng.com.systemspecs.remitarits.service.impl;

import com.google.gson.Gson;
import ng.com.systemspecs.remitarits.accountenquiry.AccountEnquiry;
import ng.com.systemspecs.remitarits.bankenquiry.GetActiveBank;
import ng.com.systemspecs.remitarits.bankenquiry.GetActiveBankResponse;
import ng.com.systemspecs.remitarits.bulkpayment.BulkPayment;
import ng.com.systemspecs.remitarits.bulkpayment.BulkPaymentRequest;
import ng.com.systemspecs.remitarits.bulkpayment.BulkPaymentResponse;
import ng.com.systemspecs.remitarits.bulkpaymentstatus.BulkPaymentStatusInfo;
import ng.com.systemspecs.remitarits.bulkpaymentstatus.BulkPaymentStatusRequest;
import ng.com.systemspecs.remitarits.bulkpaymentstatus.BulkPaymentStatusResponse;
import ng.com.systemspecs.remitarits.bulkpaymentstatus.PaymentStatusBulk;
import ng.com.systemspecs.remitarits.configuration.ConfigCredentials;
import ng.com.systemspecs.remitarits.configuration.Credentials;
import ng.com.systemspecs.remitarits.accountenquiry.AccountEnqiryRequest;
import ng.com.systemspecs.remitarits.accountenquiry.AccountEnquiryResponse;
import ng.com.systemspecs.remitarits.configuration.Environment;
import ng.com.systemspecs.remitarits.service.RemitaRITSService;
import ng.com.systemspecs.remitarits.singlepayment.SinglePayment;
import ng.com.systemspecs.remitarits.singlepayment.SinglePaymentRequest;
import ng.com.systemspecs.remitarits.singlepayment.SinglePaymentResponse;
import ng.com.systemspecs.remitarits.singlepaymentstatus.PaymentStatus;
import ng.com.systemspecs.remitarits.singlepaymentstatus.PaymentStatusRequest;
import ng.com.systemspecs.remitarits.singlepaymentstatus.PaymentStatusResponse;
import ng.com.systemspecs.remitarits.util.*;

import java.util.Map;

/**
 * The <tt>RemitaRITS</tt> class implements the six methods in <tt>RemitaRITS</tt>  for integrating with Remita
 * RITS Gateway.
 *
 *
 * For example:
 *
 * <pre class="code">
 * RemitaRITSService remitaRITSService = new RemitaRITSServiceImpl(Credentials.class);
 * remitaRITSService.getActiveBanks();
 * </pre>
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class RemitaRITSServiceImpl implements RemitaRITSService {

    private Credentials credentials;

    private Connection connection;

    private Environment environment;

    public RemitaRITSServiceImpl(Credentials credentials) {
        this.credentials = credentials;
        this.connection = new ConnectionImpl();
        this.environment = new Environment(credentials);
    }

    @Override
    public GetActiveBankResponse getActiveBanks() {
        GetActiveBankResponse response = new GetActiveBankResponse();
        try {
            if (!ConfigCredentials.isCredential(credentials)) {
                GetActiveBank data = new GetActiveBank();
                data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
                data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
                response.setStatus(ConfigCredentials.credentialStatus);
                response.setData(data);
                return response;
            }
            Map<String, String> configuration = environment.getConfiguration();
            String url = configuration.get("GET_BANK_LIST_URL");
            String getResponse = connection.sendPOST(url, credentials, configuration, null);
            return new Gson().fromJson(getResponse, GetActiveBankResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            GetActiveBank getActiveBank = new GetActiveBank();
            response.setStatus("failed");
            getActiveBank.setResponseCode(SDKResponseCode.ERROR_WHILE_CONNECTING.getCode());
            getActiveBank.setResponseDescription(SDKResponseCode.ERROR_WHILE_CONNECTING.getDescription());
            response.setData(getActiveBank);
            return response;
        }
    }

    @Override
    public AccountEnquiryResponse accountEnquiry(AccountEnqiryRequest accountEnqiryRequest) {
        AccountEnquiryResponse response = new AccountEnquiryResponse();
        try {
            if (!ConfigCredentials.isCredential(credentials)) {
                AccountEnquiry data = new AccountEnquiry();
                data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
                data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
                response.setStatus(ConfigCredentials.credentialStatus);
                response.setData(data);
                return response;
            }
            Map<String, String> configuration = environment.getConfiguration();
            String url = configuration.get("ACCOUNT_ENQUIRY_URL");
            accountEnqiryRequest = FieldEncryptionService.encryptFields(accountEnqiryRequest, credentials.getSecretKeyIv(), credentials.getSecretKey());
            String postResponse = connection.sendPOST(url, credentials, configuration, accountEnqiryRequest);
            return new Gson().fromJson(postResponse, AccountEnquiryResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            AccountEnquiry accountEnquiry = new AccountEnquiry();
            response.setStatus("failed");
            accountEnquiry.setResponseCode(SDKResponseCode.ERROR_WHILE_CONNECTING.getCode());
            accountEnquiry.setResponseDescription(SDKResponseCode.ERROR_WHILE_CONNECTING.getDescription());
            response.setData(accountEnquiry);
            return response;
        }
    }

    @Override
    public SinglePaymentResponse singlePayment(SinglePaymentRequest request) {
        SinglePaymentResponse response = new SinglePaymentResponse();
        try {
            if (!ConfigCredentials.isCredential(credentials)) {
                SinglePayment data = new SinglePayment();
                data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
                data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
                response.setStatus(ConfigCredentials.credentialStatus);
                response.setData(data);
                return response;
            }
            Map<String, String> configuration = environment.getConfiguration();
            String url = configuration.get("SINGLE_PAYMENT_URL");
            request = FieldEncryptionService.encryptFields(request, credentials.getSecretKeyIv(), credentials.getSecretKey());
            String postResponse = connection.sendPOST(url, credentials, configuration, request);
            return new Gson().fromJson(postResponse, SinglePaymentResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            SinglePayment data = new SinglePayment();
            response.setStatus("failed");
            data.setResponseCode(SDKResponseCode.ERROR_WHILE_CONNECTING.getCode());
            data.setResponseDescription(SDKResponseCode.ERROR_WHILE_CONNECTING.getDescription());
            response.setData(data);
            return response;
        }
    }

    @Override
    public PaymentStatusResponse singlePaymentStatus(PaymentStatusRequest request) {
        PaymentStatusResponse response = new PaymentStatusResponse();
        try {
            if (!ConfigCredentials.isCredential(credentials)) {
                PaymentStatus data = new PaymentStatus();
                data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
                data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
                response.setStatus(ConfigCredentials.credentialStatus);
                response.setData(data);
                return response;
            }
            Map<String, String> configuration = environment.getConfiguration();
            String url = configuration.get("SINGLE_PAYMENT_STATUS_URL");
            request = FieldEncryptionService.encryptFields(request, credentials.getSecretKeyIv(), credentials.getSecretKey());
            String postResponse = connection.sendPOST(url, credentials, configuration, request);
            return new Gson().fromJson(postResponse, PaymentStatusResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            PaymentStatus data = new PaymentStatus();
            response.setStatus("failed");
            data.setResponseCode(SDKResponseCode.ERROR_WHILE_CONNECTING.getCode());
            data.setResponseDescription(SDKResponseCode.ERROR_WHILE_CONNECTING.getDescription());
            response.setData(data);
            return response;
        }
    }

    @Override
    public BulkPaymentResponse bulkPayment(BulkPaymentRequest request) {
        BulkPaymentResponse response = new BulkPaymentResponse();
        try {
            if (!ConfigCredentials.isCredential(credentials)) {
                BulkPayment data = new BulkPayment();
                data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
                data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
                response.setStatus(ConfigCredentials.credentialStatus);
                response.setData(data);
                return response;
            }
            Map<String, String> configuration = environment.getConfiguration();
            String url = configuration.get("BULK_PAYMENT_URL");
            request = FieldEncryptionService.encyrptBulkFields(request, credentials);
            String postResponse = connection.sendPOST(url, credentials, configuration, request);
            return new Gson().fromJson(postResponse, BulkPaymentResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            BulkPayment data = new BulkPayment();
            response.setStatus("failed");
            data.setResponseCode(SDKResponseCode.ERROR_WHILE_CONNECTING.getCode());
            data.setResponseDescription(SDKResponseCode.ERROR_WHILE_CONNECTING.getDescription());
            response.setData(data);
            return response;
        }
    }

    @Override
    public BulkPaymentStatusResponse bulkPaymentStatus(BulkPaymentStatusRequest request) {
        BulkPaymentStatusResponse response = new BulkPaymentStatusResponse();
        try {
            if (!ConfigCredentials.isCredential(credentials)) {
                PaymentStatusBulk data = new PaymentStatusBulk();
                BulkPaymentStatusInfo statusInfo = new BulkPaymentStatusInfo();
                statusInfo.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
                statusInfo.setResponseMessage(ConfigCredentials.emptyCredentialsMessage);
                response.setStatus(ConfigCredentials.credentialStatus);
                response.setData(data);
                data.setBulkPaymentStatusInfo(statusInfo);
                return response;
            }
            Map<String, String> configuration = environment.getConfiguration();
            String url = configuration.get("BULK_PAYMENT_STATUS_URL");
            request = FieldEncryptionService.encryptFields(request, credentials.getSecretKeyIv(), credentials.getSecretKey());
            String postResponse = connection.sendPOST(url, credentials, configuration, request);
            return new Gson().fromJson(postResponse, BulkPaymentStatusResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("failed");
            PaymentStatusBulk data = new PaymentStatusBulk();
            BulkPaymentStatusInfo statusInfo = new BulkPaymentStatusInfo();
            statusInfo.setResponseCode(SDKResponseCode.ERROR_WHILE_CONNECTING.getCode());
            statusInfo.setResponseMessage(SDKResponseCode.ERROR_WHILE_CONNECTING.getDescription());
            response.setData(data);
            data.setBulkPaymentStatusInfo(statusInfo);
            return response;
        }
    }
}
