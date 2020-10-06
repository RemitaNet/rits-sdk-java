package ng.com.systemspecs.remitarits.configuration;

import ng.com.systemspecs.remitarits.util.ApplicationConstant;
import ng.com.systemspecs.remitarits.util.EnvironmentType;
import org.springframework.util.StringUtils;

/**
 * SystemSpecs will send your merchant ID and other Key
 * necessary to secure your handshake to the Remita platform
 * after  sign-up on Remita as merchant/biller
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class Credentials {

	/**
	 * This is your token for authentication to Remita
	 *
	 */
	private String apiKey;

	/**
	 * This is used as a one-way hashing key
	 *
	 */
	private String apiToken;

	/**
	 * This is the merchant identifier
	 *
	 */
	private String merchantId;

	/**
	 * SecretKey/ ENC_KEY is used in encrypting data
	 *
	 */
	private String secretKey;

	/**
	 * Vector/ ENC_INIT_VECTOR is used in encrypting data
	 *
	 */
	private String secretKeyIv;

	/**
	 * Sets the read timeout to a specified timeout, in
	 * milliseconds. This field is an Optional field and will automatically be
	 * set to 200000 milliseconds if not supplied.
	 *
	 */
	private int readTimeOut;

	/**
	 * Sets a specified timeout value, in milliseconds, to be used
	 * when opening a communications link to the resource referenced
	 * by this URLConnection.  If the timeout expires before the
	 * connection can be established, a java.net.SocketTimeoutException is raised.
	 * This field is an Optional field and will automatically be
	 * set to 200000 milliseconds if not supplied.
	 *
	 */
	private int connectionTimeOut;

	/**
	 * This uniquely identifies a request
	 *
	 */
	private String requestId;

	/**
	 * This field is an Optional field and can only take enum type EnvironmentType.LIVE
	 * or EnvironmentType.DEMO. It will automatically be set to "EnvironmentType.DEMO" if not supplied.
	 *
	 */
	private EnvironmentType environment;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getSecretKeyIv() {
		return secretKeyIv;
	}

	public void setSecretKeyIv(String secretKeyIv) {
		this.secretKeyIv = secretKeyIv;
	}

	public int getReadTimeOut() {
		if(this.readTimeOut == 0 || StringUtils.isEmpty(this.readTimeOut)) {
			this.readTimeOut = 200000;
		}
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	public int getConnectionTimeOut() {
		if(this.connectionTimeOut == 0 || StringUtils.isEmpty(this.connectionTimeOut)) {
			this.connectionTimeOut = 200000;
		}
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(int connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public EnvironmentType getEnvironment() {
		if(this.environment == null || StringUtils.isEmpty(this.environment)) {
			this.environment = EnvironmentType.DEMO;
		}
		return environment;
	}

	public void setEnvironment(EnvironmentType environment) {
		this.environment = environment;
	}

}
