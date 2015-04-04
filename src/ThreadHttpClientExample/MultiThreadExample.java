package ThreadHttpClientExample;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class MultiThreadExample
{
	public static int i = 0;
	public static void main(String args[])
	{
		PoolingHttpClientConnectionManager connManager1 = new PoolingHttpClientConnectionManager();
		connManager1.setMaxTotal(5000);
		connManager1.setDefaultMaxPerRoute(5000);

		long startTime = System.currentTimeMillis();
		
		CloseableHttpClient testClient1 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient2 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient3 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient4 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient5 = HttpClients.custom().setConnectionManager(connManager1).build();
	
		CloseableHttpClient testClient6 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient7 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient8 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient9 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient10 = HttpClients.custom().setConnectionManager(connManager1).build();
	
		CloseableHttpClient testClient11 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient12 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient13 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient14 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient15 = HttpClients.custom().setConnectionManager(connManager1).build();
	
		CloseableHttpClient testClient16 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient17 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient18 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient19 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient20 = HttpClients.custom().setConnectionManager(connManager1).build();
	
		
		MultiHttpClientConThread testThread1 = new MultiHttpClientConThread(testClient1);
		MultiHttpClientConThread testThread2 = new MultiHttpClientConThread(testClient2);
		MultiHttpClientConThread testThread3 = new MultiHttpClientConThread(testClient3);
		MultiHttpClientConThread testThread4 = new MultiHttpClientConThread(testClient4);
		MultiHttpClientConThread testThread5 = new MultiHttpClientConThread(testClient5);

		MultiHttpClientConThread testThread6 = new MultiHttpClientConThread(testClient6);
		MultiHttpClientConThread testThread7 = new MultiHttpClientConThread(testClient7);
		MultiHttpClientConThread testThread8 = new MultiHttpClientConThread(testClient8);
		MultiHttpClientConThread testThread9 = new MultiHttpClientConThread(testClient9);
		MultiHttpClientConThread testThread10 = new MultiHttpClientConThread(testClient10);

		MultiHttpClientConThread2 testThread11 = new MultiHttpClientConThread2(testClient11);
		MultiHttpClientConThread2 testThread12 = new MultiHttpClientConThread2(testClient12);
		MultiHttpClientConThread2 testThread13 = new MultiHttpClientConThread2(testClient13);
		MultiHttpClientConThread2 testThread14 = new MultiHttpClientConThread2(testClient14);
		MultiHttpClientConThread2 testThread15 = new MultiHttpClientConThread2(testClient15);

		MultiHttpClientConThread2 testThread16 = new MultiHttpClientConThread2(testClient16);
		MultiHttpClientConThread2 testThread17 = new MultiHttpClientConThread2(testClient17);
		MultiHttpClientConThread2 testThread18 = new MultiHttpClientConThread2(testClient18);
		MultiHttpClientConThread2 testThread19 = new MultiHttpClientConThread2(testClient19);
		MultiHttpClientConThread2 testThread20 = new MultiHttpClientConThread2(testClient20);
		
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		System.out.println("Run 1"); 
		
		ThreadPoolExecutor executorService1 = new ThreadPoolExecutor(2, 2, 30, TimeUnit.HOURS, queue);
		try
		{
			executorService1.execute(testThread1);
			executorService1.execute(testThread2); 
			executorService1.execute(testThread3);
			executorService1.execute(testThread4);
			executorService1.execute(testThread5);
			executorService1.execute(testThread6);
			executorService1.execute(testThread7);
			executorService1.execute(testThread8);
			executorService1.execute(testThread9);
			executorService1.execute(testThread10);
	
			executorService1.execute(testThread11);
			executorService1.execute(testThread12);
			executorService1.execute(testThread13);
			executorService1.execute(testThread14);
			executorService1.execute(testThread15); 
			executorService1.execute(testThread16);
			executorService1.execute(testThread17);
			executorService1.execute(testThread18);
			executorService1.execute(testThread19);
			executorService1.execute(testThread20);
		
		while (true) {
		    if ((executorService1.getActiveCount() == 0))
		    {
		    	Thread.sleep(10 * 1000);
		    	System.out.println("Total Calls made " + i);
		    	long endTime   = System.currentTimeMillis();
		    	long totalTime = endTime - startTime;
		    	NumberFormat formatter = new DecimalFormat("#0.00000");
		    	System.out.println("Execution time is - " + formatter.format((totalTime / 1000d)/60) + " minutes");
		    	System.out.println(" Rate " + formatter.format(i/((totalTime / 1000d)/60)) + " - calls per minute");
		    	System.out.println(" Rate " + formatter.format(i/((totalTime / 1000d)/60)/60) + " - calls per second");
		    	executorService1.shutdown();;
		    	break;
		    }
		}
		}
		catch(Exception Ex)
		{
			
		}
		finally
		{

		}
	}
}
