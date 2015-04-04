//http://www.baeldung.com/httpclient-connection-management
package ThreadHttpClientExample;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class MultiHttpClientConThread extends Thread
{
	private CloseableHttpClient client;
	
	public MultiHttpClientConThread(CloseableHttpClient client)
	{
		this.client = client;
	}
	
	public void run()
	{
		try
		{
			String[] exampleTestData = new String[]{"Dubai", "London", "Mumbai", "Chennai", "Bangalore"};
			for(int i = 0, j =0 ; i < 150; i++, j++)
			{
				if(j ==5 )
				{
					j = 0;
				}
				
				HttpGet httpGetRequest = new HttpGet("http://api.openweathermap.org/data/2.5/weather?q=" + exampleTestData[j] + "&mode=xml");
				HttpResponse response = client.execute(httpGetRequest);
				System.out.println("Thread Name " + Thread.currentThread().getName() + ", i Value : " + i + " , Response status resp :" + response.getStatusLine());
				MultiThreadExample.i++;
				System.out.println("Calls made " + MultiThreadExample.i + ", MultiHttpClientConThread");
			}
		}
		catch(ClientProtocolException Ex)
		{
			
		}
		catch(IOException ex)
		{
			
		}
	}
}