package cn.aofeng.threadpool4j.common;

public class SystemUtil {
	public static String getEndLine() {
		return System.getProperty("line.separator");
	}

	public static int getProcessorCount() {
		return Runtime.getRuntime().availableProcessors();
	}
}
