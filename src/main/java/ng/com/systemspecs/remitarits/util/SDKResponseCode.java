package ng.com.systemspecs.remitarits.util;

/**
 * A configuration class
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public enum SDKResponseCode {

	ERROR_WHILE_CONNECTING("251", "Error occur while connecting to RemitaRITsGateway service"),
	ERROR_PROCESSING_REQUEST("25", "Error processing RemitaRITsGateway's request"),
	TIMEOUT_CONNECTION_ERROR("013", "Timeout Connecting To RemitaRITsGateway"),
	FAILED("01", "fail"),
	TIMEOUT_EXCEPTION("252", "Connection Timed Out"),
	INVALID_ENVIRONMENT("253", "Invalid RemitaRITsGateway Environment...");
	

	private String code;

	private String description;

	SDKResponseCode(String code, String description) {
		setCode(code);
		setDescription(description);
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
