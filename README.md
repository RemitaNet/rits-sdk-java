[![Maven Central](https://img.shields.io/maven-central/v/ng.com.systemspecs/remita-rits.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22ng.com.systemspecs%22%20AND%20a:%22remita-rits%22)

# Remita Interbank Transfer Service (RITs) JAVA SDK
This is the JAVA SDK for the Remita Interbank Transfer Service simple API

# Dependencies
## Apache Maven
    <dependency>
        <groupId>ng.com.systemspecs</groupId>
        <artifactId>remita-rits</artifactId>
        <version>1.0.0</version>
    </dependency>
## Gradle Groovy DSL
    implementation 'ng.com.systemspecs:remita-rits:1.0.0'

## Gradle Kotlin DSL
    implementation("ng.com.systemspecs:remita-rits:1.0.0")

## Scala SBT
    libraryDependencies += "ng.com.systemspecs" % "remita-rits" % "1.0.0"

## Apache Ivy
    <dependency org="ng.com.systemspecs" name="remita-rits" rev="1.0.0" />

## Groovy Grape
    @Grapes(@Grab(group='ng.com.systemspecs', module='remita-rits', version='1.0.0'))

## Leiningen
    [ng.com.systemspecs/remita-rits "1.0.0"]

## Apache Buildr
    'ng.com.systemspecs:remita-rits:jar:1.0.0'

## Jar Download
 To download Jar  [CLICK](https://search.maven.org/remotecontent?filepath=ng/com/systemspecs/remita-rits/1.0.0/remita-rits-1.0.0.jar) 
## Requirements
*  Java 8 or later
*  STS , IntelliJ , Eclipse
*  Maven 4 and later

## Prerequisites
The workflow to getting started on RITs is as follows:

*  Register a profile on Remita: You can visit [Remita](https://login.remita.net) to sign-up if you are not already registered as a merchant/biller on the platform.
*  Receive the Remita credentials that certify you as a Biller: SystemSpecs will send you your merchant ID and an API Key necessary to secure your handshake to the Remita platform.

## Using the SDK 
*  Add any of the above dependency in your build tool file. E.g For MAVEN, add the dependency in maven section to your pom.xml

## Configuration
All merchant credentials needed to use RITs are being setup by instantiating the Credential Class and set the properties in this class accordingly. Properties such as MerchantId, ApiKey, ApiToken, Key, Iv and the Environment needs to be set.
 
_Note:_ Environment can either be DEMO or LIVE, each of this environment has it respective Credential. Ensure you set the right credentials. By default Environment is DEMO.
Below is a code sample on how to Initialize and set the credentials.

 ```java
        Credentials credentials = new Credentials();
        credentials.setMerchantId("DEMOMDA1234");
        credentials.setApiKey("REVNT01EQTEyMzR8REVNT01EQQ==");
        credentials.setApiToken("bmR1ZFFFWEx5R2c2NmhnMEk5a25WenJaZWZwbHFFYldKOGY0bHlGZnBZQ1N5WEpXU2Y1dGt3PT0=");
        credentials.setSecretKey("nbzjfdiehurgsxct");
        credentials.setSecretKeyIv("sngtmqpfurxdbkwj");
        credentials.setRequestId(String.valueOf(System.currentTimeMillis()));
        credentials.setEnvironment(EnvironmentType.DEMO);

        RemitaRITSService remitaRITSService = new RemitaRITSServiceImpl(credentials);
        remitaRITSService.getActiveBanks();
 ```

## Methods
#### Payments
Payments on the RITs platform can only be made from Remita-identifiable accounts. This means that before an account can be debited on the RITs, it must be linked to a profile. Merchants may process payments via the following SDK methods:

* Single Payment Request: This charges/debits a merchant’s account with a specified amount to credit a designated 	beneficiary account. Fields(payload) to set include:
	1. fromBank: This is the CBN code of the funding bank
	2. debitAccount: This is the funding account number
	3. toBank: The CBN code of destination bank where account number to be credited is domiciled. (You can use the Banks Enquiry method to get the list of all supported Banks’ code).
	4. creditAccount: This is the account number to be credited in destination bank.
	5. narration: The narration of the payment. This will typically be visible both in the debit and credit account statement. Max length 30 characters
	6. amount: The amount to be debited from the debitAccountToken and credited to creditAccount in bank toBank. Format - ##.##
	7. beneficiaryEmail: Email of the beneficiary (email of creditAccount holder)
	8. transRef: A unique reference that identifies a payment request. This reference can be used sub- sequently to retrieve the details/status of the payment request

```java
    public SinglePaymentResponse singlePayment(){
    ...
        RemitaRITSService ritsService = new RemitaRITSServiceImpl(credentials);
        SinglePaymentRequest request = new SinglePaymentRequest();
        request.setAmount("5000");
        request.setBeneficiaryEmail("qa@test.com");
        request.setCreditAccount("0582915208099");
        request.setDebitAccount("1234565678");
        request.setFromBank("044");
        request.setNarration("Regular Payment");
        request.setToBank("058");
        request.setTransRef(String.valueOf(System.currentTimeMillis()));
        SinglePaymentResponse singlePaymentResponse = ritsService.singlePayment(request);
        return singlePaymentResponse;
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
      public BulkPaymentResponse postBulkPayment() {
        ...
        RemitaRITSService ritsService = new RemitaRITSServiceImpl(credentials);
        BulkPaymentRequest request = new BulkPaymentRequest();
        BulkPaymentInfo bulkPaymentInfo = new BulkPaymentInfo();
        List<PaymentDetails> listPaymentDetails = new ArrayList<PaymentDetails>();

        PaymentDetails paymentDetails = new PaymentDetails();
        bulkPaymentInfo.setBatchRef(String.valueOf(System.currentTimeMillis()));
        bulkPaymentInfo.setDebitAccount("0581234567");
        bulkPaymentInfo.setBankCode("058");
        bulkPaymentInfo.setNarration("Regular Payment");

        paymentDetails.setAmount("8000");
        paymentDetails.setBenficiaryEmail("qa@test.com");
        paymentDetails.setTransRef(String.valueOf(System.currentTimeMillis()));
        paymentDetails.setBenficiaryBankCode("058");
        paymentDetails.setBenficiaryAccountNumber("0582915208011");
        paymentDetails.setNarration("Regular Payment");

        PaymentDetails paymentDetailsOne = new PaymentDetails();
        paymentDetailsOne.setAmount("8000");
        paymentDetailsOne.setBenficiaryEmail("qa@test.com");
        paymentDetailsOne.setTransRef(String.valueOf(System.currentTimeMillis()));
        paymentDetailsOne.setBenficiaryBankCode("058");
        paymentDetailsOne.setBenficiaryAccountNumber("0582915208012");
        paymentDetailsOne.setNarration("Regular Payment");

        PaymentDetails paymentDetailsTwo = new PaymentDetails();
        paymentDetailsTwo.setAmount("4000");
        paymentDetailsTwo.setBenficiaryEmail("qa@test.com");
        paymentDetailsTwo.setTransRef(String.valueOf(System.currentTimeMillis()));
        paymentDetailsTwo.setBenficiaryBankCode("058");
        paymentDetailsTwo.setBenficiaryAccountNumber("0582915208013");
        paymentDetailsTwo.setNarration("Regular Payment");

        listPaymentDetails.add(paymentDetails);
        listPaymentDetails.add(paymentDetailsOne);
        listPaymentDetails.add(paymentDetailsTwo);

        bulkPaymentInfo.setTotalAmount(String.valueOf(new BigDecimal(paymentDetails.getAmount())
                .add(new BigDecimal(paymentDetailsOne.getAmount()))
                .add(new BigDecimal(paymentDetailsTwo.getAmount()))));


        request.setPaymentDetails(listPaymentDetails);
        request.setBulkPaymentInfo(bulkPaymentInfo);
        BulkPaymentResponse bulkPaymentResponse = ritsService.bulkPayment(request);
        return bulkPaymentResponse;
    }


```   
#### Payment Request Status
The payment request status method essentially retrieves the status of a previous payment request(Single payment and Bulk payment) using its transaction reference.

* Single Payment Request Status:
	1. transRef: This should be the same transRef that was used for the single payment request
	2. requestId: This uniquely identifies the request


```java
    public PaymentStatusResponse singlePaymentStatus() {
    ...
        RemitaRITSService ritsService = new RemitaRITSServiceImpl(credentials);
        PaymentStatusRequest request = new PaymentStatusRequest();
        request.setTransRef("1601031122038");
        PaymentStatusResponse paymentStatus = ritsService.singlePaymentStatus(request);
        return paymentStatus;
    }
```

* Bulk Send Payment Request Status: 
	1. batchRef: This should be the same batchRef that was used for the bulk payment request
	2. requestId: This uniquely identifies the request

```java
    public BulkPaymentResponse bulkPaymentStatus(){
    ...
        RemitaRITSService ritsService = new RemitaRITSServiceImpl(credentials);
        BulkPaymentStatusRequest request = new BulkPaymentStatusRequest();
        request.setBatchRef("1601036668810");
        BulkPaymentStatusResponse bulkResponse = ritsService.bulkPaymentStatus(request);
        return bulkResponse;
    }
```

#### Account Enquiry
Payment Request Status finds all available information on a specific account, required fields(Payloads) are as follow;


	   1. accountNo: Account number of tokenized account to be looked up
	   2. bankCode: The bank code where the account is domiciled. Use the Banks Enquiry method
	   3. requestId: This uniquely identifies the request
```java
      public AccountEnquiryResponse getAccountEnquiry(){
      ...
      RemitaRITSService ritsService = new RemitaRITSServiceImpl(credentials);
        AccountEnqiryRequest accountEnqiryRequest = new AccountEnqiryRequest();
        accountEnqiryRequest.setAccountNo("044222222");
        accountEnqiryRequest.setBankCode("044");
        AccountEnquiryResponse accountEnquiryResponse = remitaRITSService.accountEnquiry(accountEnqiryRequest);
        return accountEnquiryResponse;
	}

```
#### Bank Enquiry
This method lists the banks that are active on the RITs platform. required fields(Payloads) are as follow;


```java
    public GetBillerResponse getActiveBanks(){
    ...
        RemitaRITSService ritsService = new RemitaRITSServiceImpl(credentials);
        GetBillerResponse response = gatewayService.getBillers();
        return response;
    }

```

    
## Support
- For all other support needs, support@remita.net
