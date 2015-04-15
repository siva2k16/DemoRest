package ThreadHttpClientExample;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class LoadTestExample {
	public static int noofCalls = 0;

	public static void main(String args[]) {
		PoolingHttpClientConnectionManager connManager1 = new PoolingHttpClientConnectionManager();
		connManager1.setMaxTotal(5000);
		connManager1.setDefaultMaxPerRoute(5000);

		int noOfHttpClient = 20;
		int noOfWeatherTestLocation = 10;
		int noOfWeatherTestCoordinate = 10;
		long startTime = System.currentTimeMillis();

		CloseableHttpClient[] httpTestClient = new CloseableHttpClient[noOfHttpClient];

		for (int i = 0; i < noOfHttpClient; i++) {
			httpTestClient[i] = HttpClients.custom()
					.setConnectionManager(connManager1).build();
		}

		WeatherTestLocation[] testWeatherTestLocations = new WeatherTestLocation[noOfWeatherTestLocation];
		WeatherTestCoordinate[] testWeatherTestCoordinates = new WeatherTestCoordinate[noOfWeatherTestCoordinate];

		for (int i = 0; i < noOfWeatherTestLocation; i++) {
			testWeatherTestLocations[i] = new WeatherTestLocation(httpTestClient[i]);
		}

		for (int i = 0, j = noOfWeatherTestLocation; i < noOfWeatherTestCoordinate; i++) {
			testWeatherTestCoordinates[i] = new WeatherTestCoordinate(
					httpTestClient[j++]);
		}

		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		System.out.println("Run 1");

		ThreadPoolExecutor executorService1 = new ThreadPoolExecutor(8, 8, 30,
				TimeUnit.HOURS, queue);

		for (int i = 0; i < noOfWeatherTestCoordinate; i++) {
			executorService1.execute(testWeatherTestLocations[i]);
		}
		for (int i = 0; i < noOfWeatherTestLocation; i++) {
			executorService1.execute(testWeatherTestCoordinates[i]);
		}

		try {
			while (true) {
				if ((executorService1.getActiveCount() == 0)) {
					Thread.sleep(10 * 1000);
					System.out.println("Total Calls made " + noofCalls);
					long endTime = System.currentTimeMillis();
					long totalTime = endTime - startTime;
					NumberFormat formatter = new DecimalFormat("#0.00000");
					System.out.println("Execution time is - "
							+ formatter.format((totalTime / 1000d) / 60)
							+ " minutes");
					System.out.println(" Rate "
							+ formatter.format(noofCalls
									/ ((totalTime / 1000d) / 60))
							+ " - calls per minute");
					System.out.println(" Rate "
							+ formatter.format(noofCalls
									/ ((totalTime / 1000d) / 60) / 60)
							+ " - calls per second");
					executorService1.shutdown();
					;
					break;
				}
			}
		} catch (Exception Ex) {

		} finally {

		}
	}
}
