/*
* Copyright 2015-2020 SF-Express Tech Company. 
*/
package cn.aofeng.threadpool4j.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Date: 2018年4月2日<br/>
 * 
 * @author sudo
 */
public class CounterThreadPoolExecutor extends ThreadPoolExecutor {

	// 提交任务的计数器
	private final AtomicInteger submittedTaskCount = new AtomicInteger(0);

	/**
	 * 带ThreadFactory 构造
	 * 
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param threadFactory
	 */
	public CounterThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	/**
	 * 
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 */
	public CounterThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	/**
	 * 逻辑与{@link java.util.concurrent.Executors#newFixedThreadPool
	 * Executors.newFixedThreadPool} 相同
	 * 
	 * @param fixedPoolSize
	 */
	public CounterThreadPoolExecutor(int fixedPoolSize) {
		super(fixedPoolSize, fixedPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	/**
	 * 逻辑与{@link java.util.concurrent.Executors#newFixedThreadPool
	 * Executors.newFixedThreadPool} 相同
	 * 
	 * @param fixedPoolSize
	 * @param threadFactory
	 */
	public CounterThreadPoolExecutor(int fixedPoolSize, ThreadFactory threadFactory) {
		super(fixedPoolSize, fixedPoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	@Override
	public void execute(Runnable command) {
		submittedTaskCount.incrementAndGet(); // 计数器加1
		super.execute(command);
	}

	@Override
	public void afterExecute(Runnable r, Throwable t) {
		submittedTaskCount.decrementAndGet(); // 计数器减1
		super.afterExecute(r, t);
	}

	/**
	 * 获取当前提交任务数
	 * 
	 * @return
	 */
	public int getSubmittedTaskCount() {
		return this.submittedTaskCount.get();
	}

}
