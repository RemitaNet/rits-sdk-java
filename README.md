# Remita Interbank Transfer Service (RITs) JAVA SDK
This is the JAVA SDK for the Remita Interbank Transfer Service simple API

# Package Managers
#Maven
To install the remita-rpg-sdk-java from central repository, add dependency to your application pom.xml as below.

    <dependency>
    <groupId>com.systemspecs.paymentinfra</groupId>
    <artifactId>remita-rpg-sdk-java</artifactId>
    <version>1.0-SNAPSHOT</version>
    </dependency>

Run mvn install to install dependency

## Requirements
*  Java 1.8 or later
*  STS , IntelliJ , Eclipse
*  Maven 4 and later

## Prerequisites
The workflow to getting started on RITs is as follows:

*  Register a profile on Remita: You can visit [Remita](https://login.remita.net) to sign-up if you are not already registered as a merchant/biller on the platform.
*  Receive the Remita credentials that certify you as a Biller: SystemSpecs will send you your merchant ID and an API Key necessary to secure your handshake to the Remita platform.

## Installing the SDK 

*  Download the `rits-sdk-java-master.zip` package into a directory of your choice.
*  Extract and go to the rits-sdk-java directory.
*  Open RemitaRITsGatewayMaven from your IDE.
*  Build/Rebuild the project.


## Configuration
All merchant credentials needed to use RITs are being setup by instantiating the Credential Class and set the properties 
in this class accordingly. Properties such as MerchantId, ApiKey, ApiToken, Key, Iv and the Environment needs to be set.
 
_Note:_ Environment can either be TEST or LIVE, each of this environment has it respective Credential. Ensure you set the 
right credentials. By default Environment is TEST.

## Methods
#### Adding Account(s) To Your Profile
Adding an account to your merchant profile on the RITs is a dual process. 

* The first step is to AddAccount, Fields required to add account includes the following;
	1. accountNo: This is the number of the bank account being linked to merchant profile
	2. bankCode: This is the CBN code of the bank in which the account is domiciled
	3. transRef: This uniquely identifies the transaction
	4. requestId: This uniquely identifies the request
	
 ```java
      
      @PostMapping(value = "/account")
      public String postAddAccount() throws Exception {
        Credentials credentials=new Credentials();
        credentials.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
        credentials.setApiKey("S1VESTEyMzR8S1VESQ==");
        credentials.setApiToken("dWFBTVVGTGZRUEZaemRvVC8wYVNuRkVTc2REVi9GWGdCMHRvWHNXTnovaz0=");
        credentials.setMerchantId("KUDI1234");
        credentials.setSecretKey("cymsrniuxqtgfzva");
        credentials.setSecretKeyIv("czidrfwqugpaxvkj");
        RemitaRITSService ritsService = new RemitaRITSService(credentials);
        LinkAccountRequest request = new LinkAccountRequest();
        request.setAccountNo("0441234567820");
        request.setBankCode("044");
        LinkAccountResponse accountResponse = ritsService.addAccount(request);
        return gson.toJson(accountResponse);
    }
 ```

* The second step validates the account holder via bank authentication on the account details. You will be required by 	your bank to validate the account details the AddAccount request is being issued for, required fields(Payloads) are as follow;
	1. card: This is the one of the authentication detail required by the bank from the account owner to validate 	AddAccount request
	2. otp: This is the another authentication detail required by the bank from the account owner to validate AddAccount 	request
	3. remitaTransref: This uniquely identifies the specific add account request the validation is being called for
	4. requestId: This uniquely identifies the request
	

 ```java
 
     @PostMapping(value = "/validateAccount")
    public String validateAddAccount() throws Exception {
       Credentials credentials=new Credentials();
       credentials.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
       credentials.setApiKey("S1VESTEyMzR8S1VESQ==");
       credentials.setApiToken("dWFBTVVGTGZRUEZaemRvVC8wYVNuRkVTc2REVi9GWGdCMHRvWHNXTnovaz0=");
       credentials.setMerchantId("KUDI1234");
       credentials.setSecretKey("cymsrniuxqtgfzva");
       credentials.setSecretKeyIv("czidrfwqugpaxvkj");
       RemitaRITSService ritsService = new RemitaRITSService(credentials);
       AccountValidationRequest request = new AccountValidationRequest();
        request.setRemitaTransRef("MTUxNjYwOTcxNzM3MQ==");
        List<AuthParamsRequest> paramsList = new ArrayList<>();
        AuthParamsRequest params = new AuthParamsRequest();
        params.setParam1("OTP");
        params.setValue("1234");
        paramsList.add(params);
        AuthParamsRequest paramsNew = new AuthParamsRequest();
        paramsNew.setParam2("CARD");
        paramsNew.setValue("0441234567890");
        paramsList.add(paramsNew);
        request.setAuthParams(paramsList);
        AccountValidationResponse accountValidation = ritsService.accountTokenValidate(request);
        return gson.toJson(accountValidation);
    }
   ```

Successful authentication through the bank links the designated account to the corresponding merchant profile on the
RITs platform.

#### Payments
Payments on the RITs platform can only be made from Remita-identifiable accounts. This means that before an account
can be debited on the RITs, it must be linked to a profile. Merchants may process payments via the following SDK
methods on the platform:

* Single Payment Request: This charges/debits a merchant’s account with a specified amount to credit a designated 	beneficiary account. Fields(payload) to set include:
	1. fromBank: This is the CBN code of the funding bank
	2. debitAccount: This is the funding account number
	3. toBank: The CBN code of destination bank where account number to be credited is domiciled. (You can use the Banks Enquiry method to get the list of all supported Banks’ code).
	4. creditAccount: This is the account number to be credited in destination bank.
	5. narration: The narration of the payment. This will typically be visible both in the debit and credit account statement. Max length 30 characters
	6. amount: The amount to be debited from the debitAccountToken and credited to creditAccount in bank toBank. Format - ##.##
	7. beneficiaryEmail: Email of the beneficiary (email of creditAccount holder)
	8. transRef: A unique reference that identifies a payment request. This reference can be used sub- sequently to retrieve the details/status of the payment request
	9. requestId: This uniquely identifies the request


```java


    @PostMapping(value = "/singlePayment")
    public String singlePayment() throws Exception {
        Credentials credentials=new Credentials();
        credentials.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
        credentials.setApiKey("S1VESTEyMzR8S1VESQ==");
        credentials.setApiToken("dWFBTVVGTGZRUEZaemRvVC8wYVNuRkVTc2REVi9GWGdCMHRvWHNXTnovaz0=");
        credentials.setMerchantId("KUDI1234");
        credentials.setSecretKey("cymsrniuxqtgfzva");
        credentials.setSecretKeyIv("czidrfwqugpaxvkj");
        RemitaRITSService ritsService = new RemitaRITSService(credentials);
        SinglePaymentRequest request = new SinglePaymentRequest();
        request.setAmount("5000");
        request.setBeneficiaryEmail("qa@test.com");
        request.setCreditAccount("0582915208099");
        request.setDebitAccount("1234565678");
        request.setFromBank("044");
        request.setNarration("Regular Payment");
        request.setToBank("058");
        request.setTransRef(System.currentTimeMillis() + StringUtils.EMPTY);
        SinglePaymentResponse singlePaymentResponse = ritsService.singlePayment(request);
        return gson.toJson(singlePaymentResponse);
    }




   ```



* Bulk Send Payment Request: Here, a single amount is debited to credit multiple accounts across several banks. Fields(payload) to set include the bulkPaymentInfo Parameters and paymentDetails Parameters
	
	bulkPaymentInfo Payload
	1. batchRef: A unique reference that identifies a bulk payment request.
	2. debitAccount: Funding account number
	3. bankCode: 3 digit code representing funding bank
	4. creditAccount: This is the account number to be credited in destination bank.
	5. narration: Description of the payment
	6. requestId: This uniquely identifies the request


	paymentDetails Payload
	1. beneficiaryBankCode: The CBN code of destination bank where account number to be credited is domiciled. (You can use the Banks Enquiry method to get the list of all supported Banks’ code)
	2. beneficiaryAccountNumber: This is the account number to be credited in destination bank.
	3. narration: The narration of the payment. This will typically be visible both in the debit and credit account statement. Max length 30 characters
	4. amount: The amount to be debited from the debitAccountToken and credited to creditAccount in bank toBank
	5. beneficiaryEmail: Email of the beneficiary
	6. transRef: A unique reference that identifies a payment request. This reference can be used sub- sequently to retrieve the details/status of the payment request


```java

      @PostMapping(value = "/bulkPayment")
      public String postBulkPayment() throws Exception {
        Credentials credentials=new Credentials();
        credentials.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
        credentials.setApiKey("S1VESTEyMzR8S1VESQ==");
        credentials.setApiToken("dWFBTVVGTGZRUEZaemRvVC8wYVNuRkVTc2REVi9GWGdCMHRvWHNXTnovaz0=");
        credentials.setMerchantId("KUDI1234");
        credentials.setSecretKey("cymsrniuxqtgfzva");
        credentials.setSecretKeyIv("czidrfwqugpaxvkj");
        RemitaRITSService ritsService = new RemitaRITSService(credentials);
        BulkPaymentRequest request = new BulkPaymentRequest();
        BulkPaymentInfo bulkPaymentInfo = new BulkPaymentInfo();
        List<PaymentDetails> listPaymentDetails = new ArrayList<>();
        
        PaymentDetails paymentDetails = new PaymentDetails();
        bulkPaymentInfo.setTotalAmount("20000");
        bulkPaymentInfo.setBatchRef(System.currentTimeMillis() + StringUtils.EMPTY);
        bulkPaymentInfo.setDebitAccount("1234565678");
        bulkPaymentInfo.setBankCode("044");
        bulkPaymentInfo.setNarration("Regular Payment");
        
        paymentDetails.setAmount("8000");
        paymentDetails.setBenficiaryEmail("qa@test.com");
        paymentDetails.setTransRef(System.currentTimeMillis() + StringUtils.EMPTY);
        paymentDetails.setBenficiaryBankCode("058");
        paymentDetails.setBenficiaryAccountNumber("0582915208011");
        paymentDetails.setNarration("Regular Payment");
        
        PaymentDetails paymentDetailsOne = new PaymentDetails();
        paymentDetailsOne.setAmount("8000");
        paymentDetailsOne.setBenficiaryEmail("qa@test.com");
        paymentDetailsOne.setTransRef(System.currentTimeMillis() + StringUtils.EMPTY);
        paymentDetailsOne.setBenficiaryBankCode("058");
        paymentDetailsOne.setBenficiaryAccountNumber("0582915208012");
        paymentDetailsOne.setNarration("Regular Payment");
        
        PaymentDetails paymentDetailsTwo = new PaymentDetails();
        paymentDetailsTwo.setAmount("4000");
        paymentDetailsTwo.setBenficiaryEmail("qa@test.com");
        paymentDetailsTwo.setTransRef(System.currentTimeMillis() + StringUtils.EMPTY);
        paymentDetailsTwo.setBenficiaryBankCode("058");
        paymentDetailsTwo.setBenficiaryAccountNumber("0582915208013");
        paymentDetailsTwo.setNarration("Regular Payment");
        listPaymentDetails.add(paymentDetails);
        listPaymentDetails.add(paymentDetailsOne);
        listPaymentDetails.add(paymentDetailsTwo);
    
        request.setPaymentDetails(listPaymentDetails);
        request.setBulkPaymentInfo(bulkPaymentInfo);
        BulkPaymentResponse bulkPaymentResponse = ritsService.bulkPayment(request);
        return gson.toJson(bulkPaymentResponse);
    }


```   
#### Payment Request Status
The payment request status method essentially retrieves the status of a previous payment request(Single payment and Bulk payment) using its transaction reference.

* Single Payment Request Status:
	1. transRef: This should be the same transRef that was used for the single payment request
	2. requestId: This uniquely identifies the request


```java


    @PostMapping(value = "single/status")
    public String singlePaymentStatus() throws Exception {
        Credentials credentials=new Credentials();
        credentials.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
        credentials.setApiKey("S1VESTEyMzR8S1VESQ==");
        credentials.setApiToken("dWFBTVVGTGZRUEZaemRvVC8wYVNuRkVTc2REVi9GWGdCMHRvWHNXTnovaz0=");
        credentials.setMerchantId("KUDI1234");
        credentials.setSecretKey("cymsrniuxqtgfzva");
        credentials.setSecretKeyIv("czidrfwqugpaxvkj");
        RemitaRITSService ritsService = new RemitaRITSService(credentials);
        PaymentStatusRequest request = new PaymentStatusRequest();
        request.setTransRef("293742");
        PaymentStatusResponse paymentStatus = ritsService.singlePaymentStatus(request);
        return gson.toJson(paymentStatus);
    }

```

* Bulk Send Payment Request Status: 
	1. batchRef: This should be the same batchRef that was used for the bulk payment request
	2. requestId: This uniquely identifies the request

```java

    @PostMapping(value = "/bulkstatus")
    public String bulkPaymentStatus() throws Exception {
        Credentials credentials=new Credentials();
        credentials.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
        credentials.setApiKey("S1VESTEyMzR8S1VESQ==");
        credentials.setApiToken("dWFBTVVGTGZRUEZaemRvVC8wYVNuRkVTc2REVi9GWGdCMHRvWHNXTnovaz0=");
        credentials.setMerchantId("KUDI1234");
        credentials.setSecretKey("cymsrniuxqtgfzva");
        credentials.setSecretKeyIv("czidrfwqugpaxvkj");
        RemitaRITSService ritsService = new RemitaRITSService(credentials);
        BulkPaymentStatusRequest request = new BulkPaymentStatusRequest();
        request.setBatchRef("382849");
        BulkPaymentStatusResponse bulkResponse = ritsService.bulkPaymentStatus(request);
        return gson.toJson(bulkResponse);
    }


```

#### Account Enquiry
Payment Request Status finds all available information on a specific account, required fields(Payloads) are as follow;


	   1. accountNo: Account number of tokenized account to be looked up
	   2. bankCode: The bank code where the account is domiciled. Use the Banks Enquiry method
	   3. requestId: This uniquely identifies the request





```java
      @PostMapping(value = "/accountEnquiry")
      public String getAccountEnquiry() throws Exception {
        AccountEnqiriesRequest request = new AccountEnqiriesRequest();
        Credentials credentials=new Credentials();
        credentials.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
        credentials.setApiKey("S1VESTEyMzR8S1VESQ==");
        credentials.setApiToken("dWFBTVVGTGZRUEZaemRvVC8wYVNuRkVTc2REVi9GWGdCMHRvWHNXTnovaz0=");
        credentials.setMerchantId("KUDI1234");
        credentials.setSecretKey("cymsrniuxqtgfzva");
        credentials.setSecretKeyIv("czidrfwqugpaxvkj");
        credentials.setConnectionTimeOut(2000);
        RemitaRITSService ritsService = new RemitaRITSService(credentials);
        request.setAccountNo("044222222");
        request.setBankCode("044");
        request.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
        AccountEnquiriesResponse accountEnquiry = ritsService.accountEnquiry(request);
        return gson.toJson(accountEnquiry);
	}

```
#### Bank Enquiry
This method lists the banks that are active on the RITs platform. required fields(Payloads) are as follow;


```java


    @PostMapping(value = "/banks")
    public String getActiveBanks() throws Exception {
        Credentials credentials=new Credentials();
        credentials.setRequestId(System.currentTimeMillis() + StringUtils.EMPTY);
        credentials.setApiKey("S1VESTEyMzR8S1VESQ==");
        credentials.setApiToken("dWFBTVVGTGZRUEZaemRvVC8wYVNuRkVTc2REVi9GWGdCMHRvWHNXTnovaz0=");
        credentials.setMerchantId("KUDI1234");
        credentials.setSecretKey("cymsrniuxqtgfzva");
        credentials.setSecretKeyIv("czidrfwqugpaxvkj");
        RemitaRITSService ritsService = new RemitaRITSService(credentials);
        GetActiveBankResponse activeBank = ritsService.getAllActiveBanks();
        return gson.toJson(activeBank);
    }

```

    
## Support
- For all other support needs, support@remita.net
