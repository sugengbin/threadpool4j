package cn.aofeng.threadpool4j.common;

/**
 *
 * Date: 2018年4月3日<br/>
 * 
 * @author sudo
 */
public interface IThreadPoolLifeCycle extends ILifeCycle {

	/**
	 * 获取对应线程池的正在跑任务的线程数
	 * 
	 * @param threadpoolName
	 * @return
	 */
	int getSubmittedTaskCount(String threadpoolName);
}
