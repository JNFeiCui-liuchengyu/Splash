package edu.feicui.com.splash.db;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import edu.feicui.com.splash.Until.LogUtil;

/**
 * Created by ｌ on 2016/4/29.
 */
public class AssetsDBManager {

    public static final String TAG = "TAG";

    public static void copyAssetsFileToFile(Context context, String path, File toFile) throws IOException {
        LogUtil.d(TAG,"开始拷贝");
        LogUtil.d("AssetsDBManager", "file path:" + path);
        LogUtil.d(TAG, "数据库要拷贝到" + toFile.getAbsolutePath());
        InputStream is = null;
// 输入流(用来读取当前项目的 Assets 内的 db 文本)
        BufferedInputStream bis = null;
// 输出流(用来将读取到的 db 信息写到指定目录文件 toFile 中去)
        BufferedOutputStream bos = null;
        try {
            is = context.getAssets().open(path);
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(new FileOutputStream(toFile,false));
// IO 操作
            int len = 0;
            byte[] buff = new byte[2 * 1024];
            while ((len = bis.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
// TODO: handle exception
            throw e;
        }finally{
// IO 关闭
            bos.close();
            bis.close();
            is.close();

            LogUtil.d("AssetsDBManager", "copyAssetsFileToFile end");
        }
    }
}
