////http://www.baeldung.com/httpclient-connection-management
//http://www.baeldung.com/httpclient-connection-management
package RestExample;

import java.io.InputStream;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class RestExample {
	public static void main(String args[]) {
		try {
			String[] exampleTestData = new String[] { "Dubai", "London",
					"Mumbai", "Chennai", "Bangalore" };
			for (int i = 0; i < 5; i++) {
				HttpClient httpTestClient = new DefaultHttpClient();
				HttpGet httpGetRequest = new HttpGet(
						"http://api.openweathermap.org/data/2.5/weather?q="
								+ exampleTestData[i] + "&mode=xml");
				HttpResponse httpResponse = httpTestClient
						.execute(httpGetRequest);
				System.out
						.println("===========================================================");
				System.out.println("Response status :"
						+ httpResponse.getStatusLine());
				System.out
						.println("===========================================================");
				httpGetRequest.completed();
			}
		} catch (Exception Ex) {

		} finally {

		}
	}
}