package ng.com.systemspecs.remitarits.configuration;


import ng.com.systemspecs.remitarits.util.ApplicationConstant;
import ng.com.systemspecs.remitarits.util.SDKResponseCode;

import java.util.HashMap;
import java.util.Map;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class Environment {

    private Credentials credentials;

    public Environment(Credentials credentials) {
        this.credentials = credentials;
    }

    public Map<String, String> getConfiguration() {
        Map<String, String> configurationgMap = new HashMap<>();
        String env = credentials.getEnvironment().name();

        switch (env) {
            case "DEMO":
                getEnvParameters(credentials, configurationgMap, ApplicationConstant.DEMO_URL);
                break;
            case "LIVE":
                getEnvParameters(credentials, configurationgMap, ApplicationConstant.LIVE_URL);
                break;
            default:
                configurationgMap.put(SDKResponseCode.INVALID_ENVIRONMENT.getCode(), SDKResponseCode.INVALID_ENVIRONMENT.getDescription());
        }
        return configurationgMap;
    }

    private void getEnvParameters(Credentials credentials, Map<String, String> configurationgMap, String envUrl) {
        configurationgMap.put("MERCHANT_ID", credentials.getMerchantId().trim());
        configurationgMap.put("API_KEY", credentials.getApiKey().trim());
        configurationgMap.put("API_TOKEN", credentials.getApiToken().trim());
        configurationgMap.put("KEY", credentials.getSecretKey().trim());
        configurationgMap.put("IV", credentials.getSecretKeyIv().trim());
        configurationgMap.put("ACCOUNT_ENQUIRY_URL", envUrl + "merc/fi/account/lookup");
        configurationgMap.put("GET_BANK_LIST_URL", envUrl + "fi/banks");
        configurationgMap.put("SINGLE_PAYMENT_URL", envUrl + "merc/payment/singlePayment.json");
        configurationgMap.put("SINGLE_PAYMENT_STATUS_URL", envUrl + "merc/payment/status");
        configurationgMap.put("BULK_PAYMENT_STATUS_URL", envUrl + "merc/bulk/payment/status");
        configurationgMap.put("BULK_PAYMENT_URL", envUrl + "merc/bulk/payment/send");
        configurationgMap.put("ADD_ACCOUNT_URL", envUrl + "merc/account/token/init");
        configurationgMap.put("VALIDATE_ACC_OTP__URL", envUrl + "merc/account/token/validate");
    }

}
