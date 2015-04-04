//http://nomsdom.blogspot.in/2014/01/httpclient-poolinghttpclientconnectinma.html
package PoolingHttpClientConnectionManagerExample;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
public class PoolingHttpClientConnectionManagerExample
{
	public static void main(String args[])
	{
		try
		{
			String[] exampleTestData = new String[]{"Dubai", "London", "Mumbai", "Chennai", "Bangalore"};
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
			connManager.setMaxTotal(1000);
			connManager.setDefaultMaxPerRoute(1000);
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();
			long startTime = System.currentTimeMillis();

			for(int i = 0, j =0 ; i < 500; i++, j++)
			{
				if(j ==5 )
				{
					j = 0;
				}
				HttpGet httpGetRequest = new HttpGet("http://api.openweathermap.org/data/2.5/weather?q=" + exampleTestData[j] + "&mode=xml");
				HttpResponse resp = httpClient.execute(httpGetRequest);
				System.out.println("i Value :" + i);
				System.out.println("Response status resp :" + resp.getStatusLine());
			}
	    	long endTime   = System.currentTimeMillis();
	    	long totalTime = endTime - startTime;
	    	NumberFormat formatter = new DecimalFormat("#0.00000");
	    	System.out.print("Execution time is " + formatter.format((totalTime / 1000d)/60) + " minutes");
			
			httpClient.close();
			
		}
		catch(Exception Ex)
		{
			
		}
		finally
		{
			
		}
	}
}