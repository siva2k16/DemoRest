package loadTestExample;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class WeatherTestCoordinate extends Thread {
	private CloseableHttpClient client;

	public WeatherTestCoordinate(CloseableHttpClient client) {
		this.client = client;
	}

	public void run() {
		try {
			String[] exampleTestData = new String[] { "2172797", "2172796",
					"2172795", "2172794", "2172793" };
			for (int i = 0, j = 0; i < 150; i++, j++) {
				if (j == 5) {
					j = 0;
				}

				HttpGet httpGetRequest = new HttpGet(
						"http://api.openweathermap.org/data/2.5/weather?id="
								+ exampleTestData[j]);
				HttpResponse response = client.execute(httpGetRequest);
				System.out.println("Thread Name "
						+ Thread.currentThread().getName() + ", i Value : " + i
						+ " , Response status resp :"
						+ response.getStatusLine());
				LoadTestExample.noofCalls++;
				System.out.println("Calls made " + LoadTestExample.noofCalls
						+ ", WeatherTestCoordinate");
			}

		} catch (ClientProtocolException Ex) {

		} catch (IOException ex) {

		}
	}
}