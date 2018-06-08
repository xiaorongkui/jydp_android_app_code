package com.qmkj.jydp.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Stack;

/**
 * 添加、删除当前、删除指定的activity、清空栈、求栈大小
 */
public class ActivityManager {

    private static Stack<Activity> activityStack = new Stack<>();

    private static ActivityManager activityManager = null;

    private ActivityManager() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ActivityManager getInstance() {
        if (activityManager == null) {
            synchronized (ActivityManager.class) {
                if (activityManager == null) {
                    activityManager = new ActivityManager();
                }
            }
        }
        return activityManager;
    }

    /**
     * 添加一个activity
     *
     * @param activity the activity
     */
    public void addActivity(Activity activity) {
        if (!activityStack.contains(activity)) activityStack.add(activity);

    }


    /**
     * 删除指定activity
     *
     * @param activity the activity
     */
    public void removeActivity(Activity activity) {
        if (activityStack != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 删除指定activity
     *
     * @param clazz the clazz
     */
    public void removeActivity(Class<?> clazz) {
        Activity act = null;
        if (activityStack != null && activityStack.size() > 0)
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                act = activityStack.get(i);
                if (act.getClass() == clazz) {
                    act.finish();
                    activityStack.remove(act);
                    break;
                }
            }
        if (act != null)
            act = null;
    }

    /**
     * 删除当前activity
     */
    public void removeCurrent() {
        Activity lastElement = activityStack.lastElement();
        if (lastElement != null) {
            if (!lastElement.isFinishing())
                lastElement.finish();
            activityStack.remove(lastElement);
        }

    }

    /**
     * 获取当前activity
     *
     * @return the current
     */
    public Activity getCurrent() {
        return activityStack.lastElement();
    }

    /**
     * 清空栈
     */
    public void clear() {
        for (Activity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }


    /**
     * 求栈大小
     *
     * @return the size
     */
    public int getSize() {
        return activityStack.size();
    }

    /**
     * 退出应用
     */
    public static void exit() {
        ActivityManager.getInstance().removeCurrent();
        ActivityManager.getInstance().clear();
        System.exit(0);
    }

    /**
     * 跳转activity
     *
     * @param context the context
     * @param clazz   the clazz
     */
    public static void gotoActivity(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        gotoActivity(context, intent);
    }

    /**
     * Goto activity.
     *
     * @param context the context
     * @param intent  the intent
     */
    public static void gotoActivity(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转activity
     *
     * @param context     the context
     * @param clazz       the clazz
     * @param requestCode the request code
     */
    public static void startActivityForResult(Activity context, Class<?> clazz, int requestCode) {
        Intent intent = new Intent(context, clazz);
        startActivityForResult(context, intent, requestCode);
    }

    /**
     * Start activity for result.
     *
     * @param context     the context
     * @param intent      the intent
     * @param requestCode the request code
     */
    public static void startActivityForResult(Activity context, Intent intent, int requestCode) {
        if (context == null) {
            return;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivityForResult(intent, requestCode);
    }
}
