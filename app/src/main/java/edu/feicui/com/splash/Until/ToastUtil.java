package edu.feicui.com.splash.Until;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast 管理工具类 有且只有一个Toast对象进行展示操作
 * Created by ｌ on 2016/5/4.
 */
public class ToastUtil {
    private static Toast toast;

    public static void show(Context context, String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);

        }
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }
}
