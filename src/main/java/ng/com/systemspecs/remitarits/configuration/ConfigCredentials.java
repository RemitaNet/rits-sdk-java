package ng.com.systemspecs.remitarits.configuration;

import org.springframework.util.StringUtils;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class ConfigCredentials {


    public static String emptyCredentialsResponseCode;
    public static String emptyCredentialsMessage;
    public static String credentialStatus = "fail";

    public static boolean isCredential(Credentials credentials) {
        if (credentials.getApiKey() == null || StringUtils.isEmpty(credentials.getApiKey())) {
            emptyCredentialsMessage = "ApiKey not available";
            emptyCredentialsResponseCode = "012";
            return false;

        } else if (credentials.getApiToken() == null || StringUtils.isEmpty(credentials.getApiToken())) {
            emptyCredentialsMessage = "ApiToken not available";
            emptyCredentialsResponseCode = "013";
            return false;

        } else if (credentials.getMerchantId() == null || StringUtils.isEmpty(credentials.getMerchantId())) {
            emptyCredentialsMessage = "MerchantId not available";
            emptyCredentialsResponseCode = "011";
            return false;
        } else if (credentials.getSecretKey() == null || StringUtils.isEmpty(credentials.getSecretKey())) {
            emptyCredentialsMessage = "SecretKey not available";
            emptyCredentialsResponseCode = "014";
            return false;
        } else if (credentials.getSecretKeyIv() == null || StringUtils.isEmpty(credentials.getSecretKeyIv())) {
            emptyCredentialsMessage = "SecretKeyIv not available";
            emptyCredentialsResponseCode = "015";
            return false;
        }
        return true;
    }

}
