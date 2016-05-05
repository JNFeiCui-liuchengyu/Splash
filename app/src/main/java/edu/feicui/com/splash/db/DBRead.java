package edu.feicui.com.splash.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

import edu.feicui.com.splash.Until.LogUtil;
import edu.feicui.com.splash.bean.TelclassInfo;
import edu.feicui.com.splash.bean.TelnumberInfo;

/**
 * 用来做数据库文件的读取操作
 * Created by ｌ on 2016/5/3.
 */
public class DBRead {

    public static File telFile;

    static {
        //默认位置
        String dbFileDir = "date/date/edu.feicui.com.splash/";
        File fileDir = new File(dbFileDir);
        fileDir.mkdir();//文件目录的创建
        telFile = new File(fileDir, "commonnum.db");
        LogUtil.d("DBRead", "telFile dir path: " + dbFileDir);
    }

    /**
     * 检测是否存在通讯大全DB文件
     *
     * @return
     */
    public static boolean isExistsTeldbFile() {
        //没有通讯大全File
        File toFile = DBRead.telFile;
        if (!toFile.exists() || toFile.length() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 读取TelFile这个数据库文件中的classlist这个表内的数据
     */
    public static ArrayList<TelclassInfo> readTeldbClasslist() throws Exception {
        ArrayList<TelclassInfo> classListInfos = new ArrayList<>();
        //打开DB文件
        SQLiteDatabase db = null;
        //执行查询的SQL语句select*from classlist
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery("select * from classlist", null);
            LogUtil.d("DBRead", "read teldb classlist size:" + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor
                            .getString(cursor.getColumnIndex("name"));
                    //idx为classlist表中电话的ID,根据idx值进行指定页面的跳转
                    int idx = cursor.getInt(cursor
                            .getColumnIndex("idx"));
                    TelclassInfo classlistInfo = new TelclassInfo(
                            name, idx);
                    classListInfos.add(classlistInfo);
                } while (cursor.moveToNext());

            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            try {
                cursor.close();
                db.close();
            } catch (Exception e2) {
                // TODO: handle exception
                throw e2;
            }
            LogUtil.d("DBRead", "read teldb classList end[list size]:" + classListInfos.size());
        }
        return classListInfos;
    }

    public static ArrayList<TelnumberInfo> readTeldbTable(int idx) throws Exception {
        ArrayList<TelnumberInfo> numberInfos = new ArrayList<>();
        //idx为classlist表中电话的ID,根据idx值进行指定页面的跳转
        String sql = "select * from table" + idx;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            //打开DB文件
            db = SQLiteDatabase
                    .openOrCreateDatabase(telFile, null);
            //执行查询的SQl语句select*from table + idx
            cursor = db.rawQuery(sql, null);
            LogUtil.d("DBRead", "read teldb number table size: "
                    + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor
                            .getColumnIndex("name"));
                    String number = cursor
                            .getString(cursor
                                    .getColumnIndex("number"));
                    TelnumberInfo numberInfo = new TelnumberInfo(
                            name, number);
                    numberInfos.add(numberInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            try {
                cursor.close();
                db.close();
            } catch (Exception e2) {
                // TODO: handle exception
                throw e2;
            }
            LogUtil.d("DBRead",
                    "read teldb number table end [list size]: "
                            + numberInfos.size());
        }
        return numberInfos;
    }
}
