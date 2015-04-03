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
		PoolingHttpClientConnectionManager connManager2 = new PoolingHttpClientConnectionManager();
		connManager1.setMaxTotal(5000);
		connManager1.setDefaultMaxPerRoute(5000);
		
		connManager2.setMaxTotal(5000);
		connManager2.setDefaultMaxPerRoute(5000);
		
		long startTime = System.currentTimeMillis();
		CloseableHttpClient testClient1 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient2 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient3 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient4 = HttpClients.custom().setConnectionManager(connManager1).build();
		CloseableHttpClient testClient5 = HttpClients.custom().setConnectionManager(connManager1).build();
	
		CloseableHttpClient testClient6 = HttpClients.custom().setConnectionManager(connManager2).build();
		CloseableHttpClient testClient7 = HttpClients.custom().setConnectionManager(connManager2).build();
		CloseableHttpClient testClient8 = HttpClients.custom().setConnectionManager(connManager2).build();
		CloseableHttpClient testClient9 = HttpClients.custom().setConnectionManager(connManager2).build();
		CloseableHttpClient testClient10 = HttpClients.custom().setConnectionManager(connManager2).build();
	
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

		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		System.out.println("Run 1"); 
		
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(15, 15, 10, TimeUnit.SECONDS, queue);
		executorService.execute(testThread1);
		executorService.execute(testThread2);
		executorService.execute(testThread3);
		executorService.execute(testThread4);
		executorService.execute(testThread5);
		executorService.execute(testThread6);
		executorService.execute(testThread7);
		executorService.execute(testThread8);
		executorService.execute(testThread9);
		executorService.execute(testThread10);
		
		while (true) {
		    if (executorService.getActiveCount() == 0) 
		    {
		    	System.out.println("Total Calls made " + i);
		    	long endTime   = System.currentTimeMillis();
		    	long totalTime = endTime - startTime;
		    	NumberFormat formatter = new DecimalFormat("#0.00000");
		    	System.out.print("Execution time is " + formatter.format((totalTime / 1000d)/60) + " minutes");
		    	break;
		    }
		}
	}
}
