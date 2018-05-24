package cn.aofeng.threadpool4j.common;

public interface ILifeCycle {

	/**
	 * 初始化
	 */
	void init();

	/**
	 * 销毁
	 */
	void destroy();

}
