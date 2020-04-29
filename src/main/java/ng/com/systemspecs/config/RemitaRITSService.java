package ng.com.systemspecs.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ng.com.systemspecs.dto.*;
import ng.com.systemspecs.utils.Connections;
import ng.com.systemspecs.utils.SimpleSHAHashGenerator;
import ng.com.systemspecs.utils.FieldEncryptionService;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class RemitaRITSService {


    public static String algorithm = "AES";

    public static String cipher = "AES/CBC/PKCS5PADDING";

    private static DateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    Gson gson = new Gson();

    Credentials credentials;

    public RemitaRITSService() {
    }

    public RemitaRITSService(Credentials credentials) {
        this.credentials = credentials;
        restTemplate = commonsRestTemplate(credentials);
    }


    RestTemplate restTemplate = new RestTemplate();

    public AccountEnquiriesResponse accountEnquiry(AccountEnqiriesRequest request) throws Exception {
        AccountEnquiriesResponse accountEnquiriesResponse = null;
        String url = null;
        if (!ConfigCredentials.isCredential(credentials)) {
            AccountEnquiries data = new AccountEnquiries();
            AccountEnquiriesResponse response = new AccountEnquiriesResponse();
            data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
            data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
            response.setStatus(ConfigCredentials.credentialStatus);
            response.setData(data);
            return response;
        }
        if (credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
            url = ApplicationUrl.LIVE_URL_ACCOUNT_ENQUIRY;
        } else {
            url = ApplicationUrl.DEMO_URL_ACCOUNT_ENQUIRY;
        }
        AccountEnqiriesRequest accountEnqiries = FieldEncryptionService
                .encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
        ResponseEntity<String> responseEntity = Connections.sendPostRequest(credentials, restTemplate, accountEnqiries, url);
        if (responseEntity != null && responseEntity.hasBody()) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
            accountEnquiriesResponse = gson.fromJson(responseEntity.getBody(), AccountEnquiriesResponse.class);
        }
        return accountEnquiriesResponse;
    }

    public AccountValidationResponse accountTokenValidate(AccountValidationRequest request) throws Exception {
        AccountValidationResponse accountValidationResponse = null;
        String url = null;
        if (!ConfigCredentials.isCredential(credentials)) {
            AccountValidation data = new AccountValidation();
            AccountValidationResponse response = new AccountValidationResponse();
            data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
            data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
            response.setStatus(ConfigCredentials.credentialStatus);
            response.setData(data);
            return response;
        }
        if (credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
           url = ApplicationUrl.LIVE_URL_VALIDATE_ACCOUNT;
        } else {
            url = ApplicationUrl.DEMO_URL_VALIDATE_ACCOUNT;
        }
        AccountValidationRequest accountValidationRequest = FieldEncryptionService
                .encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
        ResponseEntity<String> responseEntity = Connections.sendPostRequest(credentials, restTemplate, accountValidationRequest, url);
        if (responseEntity != null && responseEntity.hasBody()) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
            accountValidationResponse = gson.fromJson(responseEntity.getBody(), AccountValidationResponse.class);
        }
        return accountValidationResponse;
    }

    public LinkAccountResponse addAccount(LinkAccountRequest request) throws Exception {
        LinkAccountResponse linkAccountResponse = null;
        String url = null;
        if (!ConfigCredentials.isCredential(credentials)) {
            LinkAccount data = new LinkAccount();
            LinkAccountResponse response = new LinkAccountResponse();
            data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
            data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
            response.setStatus(ConfigCredentials.credentialStatus);
            response.setData(data);
            return response;
        }
        if (credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
            url = ApplicationUrl.LIVE_URL_ADD_ACCOUNT;
        } else {
            url = ApplicationUrl.DEMO_URL_ADD_ACCOUNT;
        }
        LinkAccountRequest accountDto = FieldEncryptionService
                .encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
        ResponseEntity<String> responseEntity = Connections.sendPostRequest(credentials, restTemplate, accountDto, url);
        if (responseEntity != null && responseEntity.hasBody()) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
            linkAccountResponse = gson.fromJson(responseEntity.getBody(), LinkAccountResponse.class);
        }
        return linkAccountResponse;
    }

    public BulkPaymentResponse bulkPayment(BulkPaymentRequest request) throws Exception {
        BulkPaymentResponse bulkPaymentResponse = null;
        String url = null;
        if (!ConfigCredentials.isCredential(credentials)) {
            BulkPayment data = new BulkPayment();
            BulkPaymentResponse response = new BulkPaymentResponse();
            data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
            data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
            response.setStatus(ConfigCredentials.credentialStatus);
            response.setData(data);
            return response;
        }
        if (credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
            url = ApplicationUrl.LIVE_BULK_PAYMENT;
        } else {
            url = ApplicationUrl.DEMO_BULK_PAYMENT;
        }
        BulkPaymentRequest bulkPayment = FieldEncryptionService
                .encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
        BulkPaymentInfo bulkInfo = request.getBulkPaymentInfo();
        bulkInfo = FieldEncryptionService.encryptFields(bulkInfo, algorithm, cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
        List<PaymentDetails> pDetails = request.getPaymentDetails();
        List<PaymentDetails> pDetailsResolved = new ArrayList<>();
        for (PaymentDetails payment : pDetails) {
            PaymentDetails details = FieldEncryptionService.encryptFields(payment, algorithm, cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
            pDetailsResolved.add(details);
        }
        request.setBulkPaymentInfo(bulkInfo);
        request.setPaymentDetails(pDetailsResolved);
        ResponseEntity<String> responseEntity = Connections.sendPostRequest(credentials, restTemplate, bulkPayment, url);
        if (responseEntity != null && responseEntity.hasBody()) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
            bulkPaymentResponse = gson.fromJson(responseEntity.getBody(), BulkPaymentResponse.class);
        }

        return bulkPaymentResponse;
    }

    public BulkPaymentStatusResponse bulkPaymentStatus(BulkPaymentStatusRequest request) throws Exception {
        BulkPaymentStatusResponse bulkPaymentStatusResponse = null;
        String url = null;
        if (!ConfigCredentials.isCredential(credentials)) {
            PaymentStatusBulk data = new PaymentStatusBulk();
            BulkPaymentStatusInfo statusInfo = new BulkPaymentStatusInfo();
            BulkPaymentStatusResponse response = new BulkPaymentStatusResponse();
            statusInfo.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
            statusInfo.setResponseMessage(ConfigCredentials.emptyCredentialsMessage);
            response.setStatus(ConfigCredentials.credentialStatus);
            response.setData(data);
            data.setBulkPaymentStatusInfo(statusInfo);
            return response;
        }
        if (credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
            url = ApplicationUrl.LIVE_URL_BULK_PAYMENT_STATUS;
        } else {
            url = ApplicationUrl.DEMO_URL_BULK_PAYMENT_STATUS;
        }
        BulkPaymentStatusRequest bulkPaymentStatusRequest = FieldEncryptionService
                .encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
        ResponseEntity<String> responseEntity = Connections.sendPostRequest(credentials, restTemplate, bulkPaymentStatusRequest, url);
        if (responseEntity != null && responseEntity.hasBody()) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
            bulkPaymentStatusResponse = gson.fromJson(responseEntity.getBody(), BulkPaymentStatusResponse.class);
        }
        return bulkPaymentStatusResponse;
    }

    public GetActiveBankResponse getAllActiveBanks() throws Exception {
        Gson gson = new Gson();
        GetActiveBankResponse activeBankResponse = null;
        String url = null;
        if (!ConfigCredentials.isCredential(credentials)) {
            GetActiveBank data = new GetActiveBank();
            GetActiveBankResponse response = new GetActiveBankResponse();
            data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
            data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
            response.setStatus(ConfigCredentials.credentialStatus);
            response.setData(data);
            return response;
        }
        if (credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
            url = ApplicationUrl.LIVE_GET_ACTIVE_BANKS;
        } else {
            url = ApplicationUrl.DEMO_GET_ACTIVE_BANKS;
        }
        GetActiveBankResponse bankResponse = new GetActiveBankResponse();
        ResponseEntity<String> responseEntity = Connections.sendPostRequest(credentials, restTemplate, bankResponse, url);
        if (responseEntity != null && responseEntity.hasBody()) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
            activeBankResponse = gson.fromJson(responseEntity.getBody(), GetActiveBankResponse.class);
        }
        return activeBankResponse;
    }

    public PaymentStatusResponse singlePaymentStatus(PaymentStatusRequest request) throws Exception {
        PaymentStatusResponse paymentStatusResponse = null;
        String url = null;
        if (!ConfigCredentials.isCredential(credentials)) {
            PaymentStatus data = new PaymentStatus();
            PaymentStatusResponse response = new PaymentStatusResponse();
            data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
            data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
            response.setStatus(ConfigCredentials.credentialStatus);
            response.setData(data);
            return response;
        }
        if (credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
            url = ApplicationUrl.LIVE_URL_PAYMENT_STATUS;
        } else {
            url = ApplicationUrl.DEMO_URL_PAYMENT_STATUS;
        }
        PaymentStatusRequest paymentStatusRequest = FieldEncryptionService
                .encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
        ResponseEntity<String> responseEntity = Connections.sendPostRequest(credentials, restTemplate, paymentStatusRequest, url);
        if (responseEntity != null && responseEntity.hasBody()) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
            paymentStatusResponse = gson.fromJson(responseEntity.getBody(), PaymentStatusResponse.class);
        }
        return paymentStatusResponse;
    }

    public SinglePaymentResponse singlePayment(SinglePaymentRequest request) throws Exception {
        SinglePaymentResponse singlePaymentResponse = null;
        String url = null;
        if (!ConfigCredentials.isCredential(credentials)) {
            SinglePayment data = new SinglePayment();
            SinglePaymentResponse response = new SinglePaymentResponse();
            data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
            data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
            response.setStatus(ConfigCredentials.credentialStatus);
            response.setData(data);
            return response;
        }
        if (credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
            url = ApplicationUrl.LIVE_SINGLE_PAYMENT;
        } else {
            url = ApplicationUrl.DEMO_SINGLE_PAYMENT;
        }
        SinglePaymentRequest singlePayment = FieldEncryptionService
                .encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
        ResponseEntity<String> responseEntity = Connections.sendPostRequest(credentials, restTemplate, singlePayment, url);
        if (responseEntity != null && responseEntity.hasBody()) {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
            singlePaymentResponse = gson.fromJson(responseEntity.getBody(), SinglePaymentResponse.class);
        }
        return singlePaymentResponse;
    }

    public RestTemplate commonsRestTemplate(Credentials credentials) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(credentials.getConnectionTimeOut());
        factory.setReadTimeout(credentials.getReadTimeOut());
        return new RestTemplate(factory);
    }
}
