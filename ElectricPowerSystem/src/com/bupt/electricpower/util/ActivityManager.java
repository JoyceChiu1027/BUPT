package com.bupt.electricpower.util;

import java.util.Stack;

import android.app.Activity;

public class ActivityManager {

	private static Stack<Activity> activityStack;
	private static ActivityManager instance;

	private ActivityManager() {
	}

	public static ActivityManager getScreenManager() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}

	// �˳�ջ��Activity
	public void popActivity(Activity activity) {
		if (activity != null) {
			// �ڴ��Զ��弯����ȡ����ǰActivityʱ��Ҳ������Activity�Ĺرղ���
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	// ��õ�ǰջ��Activity
	public Activity currentActivity() {
		Activity activity = null;
		if (!activityStack.empty())
			activity = activityStack.lastElement();
		return activity;
	}

	// ����ǰActivity����ջ��
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	// �˳�ջ������Activity
	public void popAllActivityExceptOne(Class cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}
}