package cn.aofeng.threadpool4j.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadFactory implements ThreadFactory {
	private AtomicLong _count = new AtomicLong(1L);

	private static final String DEFAULT_THREAD_NAME_PRIFIX = "threadpool4j-thread";

	private ThreadGroup _group;

	private String _threadNamePrefix = "threadpool4j-thread";

	public DefaultThreadFactory() {
		this("aofeng-thread");
	}

	public DefaultThreadFactory(String threadNamePrefix) {
		_threadNamePrefix = threadNamePrefix;
		ThreadGroup root = getRootThreadGroup();
		_group = new ThreadGroup(root, _threadNamePrefix);
	}

	public Thread newThread(Runnable r) {
		Thread thread = new Thread(_group, r);
		thread.setName(_threadNamePrefix + "-" + _count.getAndIncrement());
		if (thread.isDaemon()) {
			thread.setDaemon(false);
		}
		if (5 != thread.getPriority()) {
			thread.setPriority(5);
		}

		return thread;
	}

	private ThreadGroup getRootThreadGroup() {
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		while (null != threadGroup.getParent()) {
			threadGroup = threadGroup.getParent();
		}

		return threadGroup;
	}
}