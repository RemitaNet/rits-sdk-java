package ng.com.systemspecs.remitarits.util;

import ng.com.systemspecs.remitarits.configuration.Credentials;

import java.io.IOException;
import java.util.Map;

/**
 * The <tt>Connection</tt> interface provides three methods for establishing HTTP connection with Remita
 * RITS Gateway APIs.
 *
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public interface Connection {


	/**
	 * This method sends HTTP POST request to a given URL
	 *
	 * @param url holds the URL to Remita RITS Gateway API we're trying to call
	 * @param payloadObject Object that holds the request payload
	 * @param credentials
	 * @see Credentials
	 */
	String sendPOST(String url, Credentials credentials, Map<String, String> configuration, Object payloadObject) throws IOException;

}
