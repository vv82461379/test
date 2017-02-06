package mytest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import javax.enterprise.inject.New;

public class SempDemo implements Runnable{
	
	final Semaphore semaphore = new Semaphore(5);
	
	public void run(){
		try {
			semaphore.acquire();
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId() + ":done!");
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		// 表述了异步执行的机制，并且可以让任务在后台执行。壹個 ExecutorService 实例因此特别像壹個线程池。
		//事实上，在 java.util.concurrent 包中的 ExecutorService 的实现就是壹個线程池的实现。
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		final SempDemo demo = new SempDemo();
		for(int i = 0; i < 20; i++){
			executorService.submit(demo);
		}
	}

}
