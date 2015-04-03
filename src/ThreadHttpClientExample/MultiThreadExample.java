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

		MultiHttpClientConThread testThread11 = new MultiHttpClientConThread(testClient1);
		MultiHttpClientConThread testThread12 = new MultiHttpClientConThread(testClient2);
		MultiHttpClientConThread testThread13 = new MultiHttpClientConThread(testClient3);
		MultiHttpClientConThread testThread14 = new MultiHttpClientConThread(testClient4);
		MultiHttpClientConThread testThread15 = new MultiHttpClientConThread(testClient5);

		MultiHttpClientConThread testThread16 = new MultiHttpClientConThread(testClient6);
		MultiHttpClientConThread testThread17 = new MultiHttpClientConThread(testClient7);
		MultiHttpClientConThread testThread18 = new MultiHttpClientConThread(testClient8);
		MultiHttpClientConThread testThread19 = new MultiHttpClientConThread(testClient9);
		MultiHttpClientConThread testThread20 = new MultiHttpClientConThread(testClient10);
		
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		System.out.println("Run 1"); 
		
		ThreadPoolExecutor executorService1 = new ThreadPoolExecutor(15, 15, 10, TimeUnit.SECONDS, queue);
		
		ThreadPoolExecutor executorService2 = new ThreadPoolExecutor(15, 15, 10, TimeUnit.SECONDS, queue);
		
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

		executorService2.execute(testThread11);
		executorService2.execute(testThread12);
		executorService2.execute(testThread13);
		executorService2.execute(testThread14);
		executorService2.execute(testThread15);
		executorService2.execute(testThread16);
		executorService2.execute(testThread17);
		executorService2.execute(testThread18);
		executorService2.execute(testThread19);
		executorService2.execute(testThread20);
		
		while (true) {
		    if ((executorService1.getActiveCount() == 0) && (executorService2.getActiveCount() == 0))
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
