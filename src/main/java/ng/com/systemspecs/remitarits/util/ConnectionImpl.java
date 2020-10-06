package ng.com.systemspecs.remitarits.util;



import com.google.gson.Gson;
import ng.com.systemspecs.remitarits.configuration.Credentials;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * The <tt>ConnectionImpl</tt> class implements the three methods <tt>Connection</tt> for establishing HTTP connection with Remita
 * RITS Gateway APIs.
 *
 * @author Ilesanmi Omoniyi
 *
 * @since 1.0.0
 */
public class ConnectionImpl implements Connection {

	public final static String POST_METHOD = "POST";

	public String sendPOST(String urlString, Credentials credentials, Map<String, String> configuration, Object payloadObject) throws IOException {
		StringBuffer response = null;
		DateFormat dFromat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		dFromat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String apiKey = configuration.get("API_KEY");
		String  requestId = credentials.getRequestId();
		String timeStampString = dFromat.format(new Date());
		String apiToken = configuration.get("API_TOKEN");
		String requesthash = SimpleSHAHashGenerator.generateSHA512SecurePassword(String.format("%s%s%s", apiKey, requestId, apiToken));
		URL url;
		String payload = new Gson().toJson(payloadObject);

			url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(POST_METHOD);
			con.setRequestProperty("API_KEY", apiKey);
			con.setRequestProperty("MERCHANT_ID", configuration.get("MERCHANT_ID"));
			con.setRequestProperty("REQUEST_ID", requestId);
			con.setRequestProperty("REQUEST_TS", timeStampString);
			con.setRequestProperty("API_DETAILS_HASH", requesthash);
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.setConnectTimeout(credentials.getConnectionTimeOut());
			con.setReadTimeout(credentials.getReadTimeOut());
			con.setDoOutput(true);
			OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
			os.write(payload.toString());
			os.flush();
			os.close();

			int responseCode = con.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			}
		return response.toString();
	}

}
