package com.qmkj.jydp.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.qmkj.jydp.JYDPExchangeApp;
import com.qmkj.jydp.R;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.ui.widget.FixedSpeedScroller;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 */
public class CommonUtil {


    /**
     * 跳转activity
     */
    public static void gotoActivity(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        gotoActivity(context, intent);
    }

    public static void gotoActivity(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转activity
     */
    public static void startActivityForResult(Activity context, Class<?> clazz, int requestCode) {
        Intent intent = new Intent(context, clazz);
        startActivityForResult(context, intent, requestCode);
    }

    public static void startActivityForResult(Activity context, Intent intent, int requestCode) {
        if (context == null) {
            return;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int color) {
        return getContext().getResources().getColor(color);
    }

    /**
     * Gets text size.
     *
     * @param id the id
     * @return the text size
     */
    public static int getTextSize(int id) {
        return getContext().getResources().getDimensionPixelOffset(id);
    }

    /**
     * 获取zi
     *
     * @param textId the text id
     * @return the string
     */
    public static String getString(int textId) {
        return getContext().getResources().getString(textId);
    }

    public static Drawable getDrawable(int res) {
        return getContext().getResources().getDrawable(res);
    }

    public static Bitmap getBitmap(int res) {
        return BitmapFactory.decodeResource(getContext().getResources(), res);
    }

    /**
     * 获取颜色
     */
    public static Drawable getColorDarwable(int color) {
        return getContext().getResources().getDrawable(color);
    }

    public static String[] getStringArr(int strArrId) {
        return getContext().getResources().getStringArray(strArrId);
    }

    /**
     * xml转换view
     *
     * @param layoutId
     * @return
     */
    public static View getXmlView(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    /**
     * dp与px转换
     * 1dp----0.5px
     * 1dp----1px；
     * 1dp----1.25pd
     * 1dp---1.75px;
     *
     * @param dp
     * @return int
     */
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    /**
     * 获取状态栏高度
     *
     * @return px
     */
    public static int getStatusBarHeight() {
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        return resourceId > 0 ? getContext().getResources().getDimensionPixelSize(resourceId) : 0;
    }

    private static final int INVALID_VAL = -1;


    /**
     * 设置沉浸式状态栏
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBar(Activity activity, int statusColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0以上版本
            if (statusColor != INVALID_VAL) {
                activity.getWindow().setStatusBarColor(statusColor);
            }
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build
                .VERSION_CODES.LOLLIPOP) {//4.4-5.0
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int color = Color.parseColor("#20000000");
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, getStatusBarHeight());
            statusBarView.setBackgroundColor(color);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusBarView, lp);
            //给父容器设置参数
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            contentView.getChildAt(0).setFitsSystemWindows(true);
            contentView.setClipToPadding(true);
        }
    }


    /**
     * 检查SD卡是否存在
     */
    private static boolean checkSdCard() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取上下文
     */
    public static Context getContext() {
        return JYDPExchangeApp.getContext();
    }

    public static String getFileNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }


    //隐藏键盘
    public static void hideInputWindow(Activity context) {
        if (context == null) {
            return;
        }
        final View v = ((Activity) context).getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static void hideLightWindow(Activity context, boolean isHide) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = isHide ? 0.5f : 1.0f;
        lp.dimAmount = isHide ? 0.5f : 1.0f;
        context.getWindow().setAttributes(lp);
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public static int sp2px(float spValue) {
        float fontScale = JYDPExchangeApp.getContext().getResources().getDisplayMetrics()
                .scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        float fontScale = JYDPExchangeApp.getContext().getResources().getDisplayMetrics()
                .scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 是否全屏显示
     *
     * @param context the context
     * @param isShow  the is show
     */
    public static void setStatusBarInvisible(RxAppCompatActivity context, boolean isShow) {
        int option;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = context.getWindow().getDecorView();

            if (isShow) {
                option = View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                CommonUtil.setStatusBar(context, CommonUtil.getColor(R.color.status_bar_color));
            } else {
                option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                //设置状态栏颜色为透明
                context.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
            decorView.setSystemUiVisibility(option);

        } else {
            CommonUtil.setStatusBar(context, CommonUtil.getColor(R.color.status_bar_color));
        }
        //隐藏标题栏
        ActionBar actionBar = context.getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    /**
     * Bytes to hex string string.
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    public static String formatMoney(double str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("##,##0.00");
        return myformat.format(str);
    }

    /**
     * Format money string.
     *
     * @param str the str
     * @return the string
     */
    public static String formatMoney(String str) {
        double doubleMoney = 0;
        try {
            doubleMoney = Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("formatMoney exception!");
        }
        return formatMoney(doubleMoney);
    }

    public static String format2Point(double str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("#0.00");
        return myformat.format(str);
    }

    public static String format2PointMillion(String str) {
        return format2Point(div(str, "10000"));
    }

    public static String format2Point(String str) {
        double doubleMoney = 0;
        try {
            doubleMoney = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LogUtil.i("formatMoney exception!");
        }
        return format2Point(doubleMoney);
    }

    public static String format0Point(String str) {
        double doubleMoney = 0;
        try {
            doubleMoney = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LogUtil.i("formatMoney exception!");
        }
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("#");
        return myformat.format(doubleMoney);
    }


    //获取分辨率
    public static String getAndroidPix(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return "手机屏幕分辨率为：" + dm.widthPixels + " × " + dm.heightPixels;
    }

    /**
     * 创建文件目录，用于保存文件，便于统一管理,当程序被卸载的时候会自动清除该目录下的所有文件
     *
     * @param filename 文件名
     * @return 文件全路径
     */
    public static String createDir(String dirName, String filename) {
        File externalCacheDir = getContext().getExternalCacheDir();
        File file = new File(externalCacheDir, dirName);
        if (!file.exists()) file.mkdirs();
        // 若不存在，创建目录，可以在应用启动的时候创建
        return file.getAbsolutePath() + "/" + filename;
    }

    public static String getCacheDir() {
        File externalCacheDir = getContext().getExternalCacheDir();
        // 若不存在，创建目录，可以在应用启动的时候创建
        return externalCacheDir.getAbsolutePath();
    }

    /**
     * 创建文件目录，用于保存文件，便于统一管理,当程序被卸载的时候会自动清除该目录下的所有文件
     *
     * @param filename 文件名
     * @return 文件全路径
     */
    public static String createSDCardDir(String dirName, String filename) {
        File externalCacheDir = getContext().getFilesDir();
        File file = new File(externalCacheDir, dirName);
        if (!file.exists()) file.mkdirs();
        // 若不存在，创建目录，可以在应用启动的时候创建
        return file.getAbsolutePath() + "/" + filename;
    }


    public static int getRBGColor(int r, int g, int b) {
        return Color.rgb(r, g, b);
    }

    /**
     * 提供精确的除法运算方法div,保留两位小数
     *
     * @param value1       被除数
     * @param value2       除数
     * @param roundingMode 精确方式RoundingMode.HALF_EVEN
     * @param pointCount   保留到小数位数
     * @return 两个参数的商
     */
    public static double div(double value1, double value2, RoundingMode roundingMode, int
            pointCount) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.divide(b2, pointCount, roundingMode).doubleValue();
    }

    //四舍五入，保留2位小数
    public static double div(double value1, double value2) {
        return div(value1, value2, RoundingMode.HALF_EVEN, 2);
    }

    //四舍五入，保留2位小数
    public static double div(String value1, String value2) {

        double m = 0;
        double n = 0;
        try {
            m = Double.parseDouble(value1);
            n = Double.parseDouble(value2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (n == 0) {
            return 0;
        }
        return div(m, n, RoundingMode.HALF_EVEN, 2);
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).doubleValue();
    }

    public static float getDimen(int id) {
        return JYDPExchangeApp.getContext().getResources().getDimension(id);
    }


    public static void aysncWebView(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        String cookieString = SPHelper.getInstance().getString("");
        cookieManager.setCookie(url, cookieString);
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 返回当前程序版本名
     */
    public static int getAppVersionCode(Context context) {
        int versioncode = -1;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            LogUtil.i(e.getMessage());
        }
        return versioncode;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            LogUtil.i(e.getMessage());
        }
        return versionName;
    }

    //键盘
    public static void hideKeyBoard(Activity aty) {
        try {
            ((InputMethodManager) aty.getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(aty.getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查是否是移动网络
     */
    public static boolean isMobile(Context context) {

        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) return true;
        }
        return false;
    }

    private static NetworkInfo getNetworkInfo(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 检查是否有网络
     */
    public static boolean isNetworkAvailable(Context context) {

        NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isAvailable();
    }

    /**
     * 检查是否是WIFI
     */
    public static boolean isWifi(Context context) {

        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) return true;
        }
        return false;
    }


    /**
     * 获取渠道编号
     *
     * @return 异常情况返回null
     * @author Rongkui.xiao
     * created at 2017/6/22 9:33
     */
    public static String getChannelCode(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String channelName = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo
                    (context.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null) {
                if (applicationInfo.metaData != null) {
                    channelName = applicationInfo.metaData.get(key) + "";
                }
            }

        } catch (Exception e) {
            LogUtil.i("获取设备市场渠道异常=" + e.getMessage());
        }
        return channelName;
    }

    /**
     * 获取META-INFO下面的渠道
     *
     * @param context the context
     * @return channel
     */
    public static String getChannel(Context context) {
        return "";
    }


    /**
     * Sets scroller time.
     *
     * @param mContext     the m context
     * @param scrollerTime the scroller time
     * @param viewPager    the view pager
     */
    public static void setScrollerTime(Context mContext, int scrollerTime, ViewPager viewPager) {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);

            FixedSpeedScroller mScroller = new FixedSpeedScroller(mContext, new
                    AccelerateInterpolator());
            mScroller.setmDuration(scrollerTime);    //在这里设置时间单位毫秒
            mField.set(viewPager, mScroller); //viewPager和FixedSpeedScrolle
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备id
     *
     * @author Rongkui.xiao
     * created at 2017/6/22 9:23
     */
    @SuppressLint("MissingPermission")
    public static String getPhoneIMEI(final Activity cxt) {
        TelephonyManager tm = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        //        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void setViewEnable(View view, boolean isEnable) {
        if (isEnable) {
            view.setEnabled(true);
            view.setAlpha(1.0f);
        } else {
            view.setEnabled(false);
            view.setAlpha(0.5f);
        }
    }

    /*保存注册时个人账户*/
    public static void setUserAccount(String account) {
        SPHelper.getInstance().set(Constants.SP_SAVE_LOGIN_USERINFO, account);
    }

    public static String getUserAccount() {
        return SPHelper.getInstance().getString(Constants.SP_SAVE_LOGIN_USERINFO);
    }

    /*保存登录时个人信息返回*/
    public static void setLoginInfo(LoginRes userInfo) {
        SPHelper.getInstance().saveObject(Constants.SP_SAVE_LOGIN_USERINFO, userInfo);
    }

    /*保存登录时个人信息返回*/
    public static LoginRes getLoginInfo() {
        return (LoginRes) SPHelper.getInstance().getObject(Constants.SP_SAVE_LOGIN_USERINFO, null);
    }

    //获取tokenId
    public static String getToken() {
        LoginRes loginInfo = getLoginInfo();
        if (loginInfo != null) {
            return loginInfo.getToken();
        }
        return "";
    }

    /**
     * 获取屏幕宽
     *
     * @param context
     * @return
     */
    @SuppressLint("ObsoleteSdkInt")
    public static int getScreenWidth(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            return display.getWidth();
        } else {
            return getScreenPoint(context).x;
        }
    }

    /**
     * 获取屏幕高
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            return display.getHeight();
        } else {
            return getScreenPoint(context).y;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenPoint(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        Display display = wm.getDefaultDisplay();
        display.getSize(point);
        return point;
    }

    public static String getVolUnit(float num) {

        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return "亿手";
        } else if (e >= 4) {
            return "万手";
        } else {
            return "手";
        }
    }

    public static int getVolUnitNum(float num) {

        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return 8;
        } else if (e >= 4) {
            return 4;
        } else {
            return 1;
        }
    }

    public static String getVolUnitText(int unit, float num) {
        DecimalFormat mFormat;
        if (unit == 1) {
            mFormat = new DecimalFormat("#0");
        } else {
            mFormat = new DecimalFormat("#0.00");
        }
        num = num / unit;
        if (num == 0) {
            return "0";
        }
        return mFormat.format(num);
    }

    public static String getDecimalFormatVol(float vol) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(vol);//format 返回的是字符串
    }
}
