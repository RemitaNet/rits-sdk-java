package com.systemspecs.paymentinfra.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpg.util.SimpleSHAHashGenerator;
import com.systemspecs.paymentinfra.dto.*;
import com.systemspecs.paymentinfra.utils.FieldEncryptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class RemitaRITSService {


	private static DateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	Gson gson = new Gson();

	Credentials credentials;
	
	public RemitaRITSService(){}
	public RemitaRITSService(Credentials credentials){
		this.credentials=credentials;
		restTemplate = commonsRestTemplate(credentials);
	}



	RestTemplate restTemplate = new RestTemplate();

	public AccountEnquiriesResponse accountEnquiry(AccountEnqiriesRequest request) throws Exception {
		AccountEnquiriesResponse accountEnquiriesResponse = null;
		if(!ConfigCredentials.isCredential(credentials)){
			AccountEnquiries data = new AccountEnquiries();
			AccountEnquiriesResponse response = new AccountEnquiriesResponse();
			data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
			data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
			response.setStatus(ConfigCredentials.credentialStatus);
			response.setData(data);
			return response;
		}
		if(credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
			AccountEnqiriesRequest accountEnqiries = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(accountEnqiries, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.LIVE_URL_ACCOUNT_ENQUIRY, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				accountEnquiriesResponse = gson.fromJson(responseEntity.getBody(), AccountEnquiriesResponse.class);
			}
		}else{
			AccountEnqiriesRequest accountEnqiries = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(accountEnqiries, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.DEMO_URL_ACCOUNT_ENQUIRY, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				accountEnquiriesResponse = gson.fromJson(responseEntity.getBody(), AccountEnquiriesResponse.class);
			}

		}
		return accountEnquiriesResponse;
	}

	public AccountValidationResponse accountTokenValidate(AccountValidationRequest request) throws Exception {
		AccountValidationResponse accountValidationResponse = null;
		if(!ConfigCredentials.isCredential(credentials)){
			AccountValidation data = new AccountValidation();
			AccountValidationResponse response = new AccountValidationResponse();
			data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
			data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
			response.setStatus(ConfigCredentials.credentialStatus);
			response.setData(data);
			return response;
		}
		if(credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
			AccountValidationRequest accountValidationRequest = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(accountValidationRequest, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.LIVE_URL_VALIDATE_ACCOUNT, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				accountValidationResponse = gson.fromJson(responseEntity.getBody(), AccountValidationResponse.class);
			}
			}else{
				AccountValidationRequest accountValidationRequest = FieldEncryptionService
						.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
				HttpHeaders headers = new HttpHeaders();
				String apiKey = credentials.getApiKey();
				String apiToken = credentials.getApiToken();
				String merchantId = credentials.getMerchantId();
			    String requestId = credentials.getRequestId();
				String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
				timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
				headers.add("MERCHANT_ID", merchantId);
				headers.add("API_KEY", apiKey);
				headers.add("REQUEST_ID", requestId);
				headers.add("REQUEST_TS", timestamp.format(new Date()));
				headers.add("API_DETAILS_HASH", requesthash);
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity httpEntity = new HttpEntity(accountValidationRequest, headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.DEMO_URL_VALIDATE_ACCOUNT, HttpMethod.POST, httpEntity, String.class);
				if (responseEntity != null && responseEntity.hasBody()) {
					JsonParser parser = new JsonParser();
					JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
					accountValidationResponse = gson.fromJson(responseEntity.getBody(), AccountValidationResponse.class);
				}
			}

		return accountValidationResponse;
	}

	public LinkAccountResponse addAccount(LinkAccountRequest request) throws Exception {
		LinkAccountResponse linkAccountResponse = null;
		if(!ConfigCredentials.isCredential(credentials)){
			LinkAccount data = new LinkAccount();
			LinkAccountResponse response = new LinkAccountResponse();
			data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
			data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
			response.setStatus(ConfigCredentials.credentialStatus);
			response.setData(data);
			return response;
		}
		if(credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
			LinkAccountRequest accountDto = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(accountDto, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.LIVE_URL_ADD_ACCOUNT, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				linkAccountResponse = gson.fromJson(responseEntity.getBody(), LinkAccountResponse.class);
			}
		}else{
			LinkAccountRequest accountDto = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(accountDto, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.DEMO_URL_ADD_ACCOUNT, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				linkAccountResponse = gson.fromJson(responseEntity.getBody(), LinkAccountResponse.class);
			}

		}
		return linkAccountResponse;
	}

	public BulkPaymentResponse bulkPayment(BulkPaymentRequest request) throws Exception {
		BulkPaymentResponse bulkPaymentResponse = null;
		if(!ConfigCredentials.isCredential(credentials)){
			BulkPayment data = new BulkPayment();
			BulkPaymentResponse response = new BulkPaymentResponse();
			data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
			data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
			response.setStatus(ConfigCredentials.credentialStatus);
			response.setData(data);
			return response;
		}
		if(credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
			BulkPaymentRequest bulkPayment = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(bulkPayment, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.LIVE_BULK_PAYMENT, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				bulkPaymentResponse = gson.fromJson(responseEntity.getBody(), BulkPaymentResponse.class);
			}
		}else {
			BulkPaymentRequest bulkPayment = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(bulkPayment, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.DEMO_BULK_PAYMENT, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				bulkPaymentResponse = gson.fromJson(responseEntity.getBody(), BulkPaymentResponse.class);
			}

		}
		return bulkPaymentResponse;
	}

	public BulkPaymentStatusResponse bulkPaymentStatus(BulkPaymentStatusRequest request) throws Exception {
		BulkPaymentStatusResponse bulkPaymentStatusResponse = null;
		if(!ConfigCredentials.isCredential(credentials)){
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
		if(credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
			BulkPaymentStatusRequest bulkPaymentStatusRequest = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			headers.add("MERCHANT_ID", merchantId);
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(bulkPaymentStatusRequest, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.LIVE_URL_BULK_PAYMENT_STATUS, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				bulkPaymentStatusResponse = gson.fromJson(responseEntity.getBody(), BulkPaymentStatusResponse.class);
			}
		}else {
			BulkPaymentStatusRequest bulkPaymentStatusRequest = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			headers.add("MERCHANT_ID", merchantId);
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(bulkPaymentStatusRequest, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.DEMO_URL_BULK_PAYMENT_STATUS, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				bulkPaymentStatusResponse = gson.fromJson(responseEntity.getBody(), BulkPaymentStatusResponse.class);
			}


		}
		return bulkPaymentStatusResponse;
	}

	public GetActiveBankResponse getAllActiveBanks() throws Exception {
		Gson gson = new Gson();
		GetActiveBankResponse activeBankResponse = null;
		if(!ConfigCredentials.isCredential(credentials)){
			GetActiveBank data = new GetActiveBank();
			GetActiveBankResponse response = new GetActiveBankResponse();
			data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
			data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
			response.setStatus(ConfigCredentials.credentialStatus);
			response.setData(data);
			return response;
		}
		if(credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
			GetActiveBankResponse bankResponse = new GetActiveBankResponse();
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity requestEntity = new HttpEntity(bankResponse, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.LIVE_GET_ACTIVE_BANKS, HttpMethod.POST, requestEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				activeBankResponse = gson.fromJson(responseEntity.getBody(), GetActiveBankResponse.class);
			}
		}else {
			GetActiveBankResponse bankResponse = new GetActiveBankResponse();
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("MERCHANT_ID", merchantId);
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity requestEntity = new HttpEntity(bankResponse, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.DEMO_GET_ACTIVE_BANKS, HttpMethod.POST, requestEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				activeBankResponse = gson.fromJson(responseEntity.getBody(), GetActiveBankResponse.class);
			}
		}
		return activeBankResponse;
	}

	public PaymentStatusResponse singlePaymentStatus(PaymentStatusRequest request) throws Exception {
		PaymentStatusResponse paymentStatusResponse = null;
		if(!ConfigCredentials.isCredential(credentials)){
			PaymentStatus data = new PaymentStatus();
			PaymentStatusResponse response = new PaymentStatusResponse();
			data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
			data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
			response.setStatus(ConfigCredentials.credentialStatus);
			response.setData(data);
			return response;
		}
		if(credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
			PaymentStatusRequest paymentStatusRequest = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			String requestId = credentials.getRequestId();
			headers.add("MERCHANT_ID", merchantId);
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(paymentStatusRequest, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.LIVE_URL_PAYMENT_STATUS, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				paymentStatusResponse = gson.fromJson(responseEntity.getBody(), PaymentStatusResponse.class);
			}
		}else{
			PaymentStatusRequest paymentStatusRequest = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			headers.add("MERCHANT_ID", merchantId);
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(paymentStatusRequest, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.DEMO_URL_PAYMENT_STATUS, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				paymentStatusResponse = gson.fromJson(responseEntity.getBody(), PaymentStatusResponse.class);
			}

		}
		return paymentStatusResponse;
	}

	public SinglePaymentResponse singlePayment(SinglePaymentRequest request) throws Exception {
		SinglePaymentResponse singlePaymentResponse = null;
		if(!ConfigCredentials.isCredential(credentials)){
			SinglePayment data = new SinglePayment();
			SinglePaymentResponse response = new SinglePaymentResponse();
			data.setResponseCode(ConfigCredentials.emptyCredentialsResponseCode);
			data.setResponseDescription(ConfigCredentials.emptyCredentialsMessage);
			response.setStatus(ConfigCredentials.credentialStatus);
			response.setData(data);
			return response;
		}
		if(credentials.getEnvironment().equalsIgnoreCase("LIVE")) {
			SinglePaymentRequest singlePayment = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();
			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			headers.add("MERCHANT_ID", merchantId);
			DateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(singlePayment, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.LIVE_SINGLE_PAYMENT, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				singlePaymentResponse = gson.fromJson(responseEntity.getBody(), SinglePaymentResponse.class);
			}
		}else {
			SinglePaymentRequest singlePayment = FieldEncryptionService
					.encryptFields(request, ApplicationUrl.algorithm, ApplicationUrl.cipher, credentials.getSecretKeyIv(), credentials.getSecretKey());
			HttpHeaders headers = new HttpHeaders();

			String apiKey = credentials.getApiKey();
			String apiToken = credentials.getApiToken();
			String merchantId = credentials.getMerchantId();
			headers.add("MERCHANT_ID", merchantId);
			DateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			String requestId = credentials.getRequestId();
			String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
			timestamp.setTimeZone(TimeZone.getTimeZone("UTC"));
			headers.add("API_KEY", apiKey);
			headers.add("REQUEST_ID", requestId);
			headers.add("REQUEST_TS", timestamp.format(new Date()));
			headers.add("API_DETAILS_HASH", requesthash);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(singlePayment, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(ApplicationUrl.DEMO_SINGLE_PAYMENT, HttpMethod.POST, httpEntity, String.class);
			if (responseEntity != null && responseEntity.hasBody()) {
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject) parser.parse(responseEntity.getBody());
				singlePaymentResponse = gson.fromJson(responseEntity.getBody(), SinglePaymentResponse.class);
			}
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
