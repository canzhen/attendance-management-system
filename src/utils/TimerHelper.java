package utils;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

public class TimerHelper {
	private static Timer timer = new Timer();
	
	/**
	 * 开始执行计时器
	 * @param task 要执行的任务类
	 * @param delay 延后执行的毫秒数
	 */
	public static void startTimer(TimerTask task,long delay){
		timer.schedule(task, delay);
	}
	
	/**
	 * 重置Timer
	 */
	public static void reset(){
		timer = new Timer();
	}
		
	//这是启动计时器的方法
	//TimerHelper.startTimer(new StudentAbsenceDBHelperTimerTask("13301085","cs001"), 0);
}
