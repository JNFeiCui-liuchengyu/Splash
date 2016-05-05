package edu.feicui.com.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import edu.feicui.com.splash.Until.ToastUtil;
import edu.feicui.com.splash.bean.TelclassInfo;
import edu.feicui.com.splash.db.AssetsDBManager;
import edu.feicui.com.splash.db.DBRead;

//通讯大全页面
public class TelmsgActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    /**
     * 全局定义listView空件
     */
    private ListView mListView;
    //全局定义TelclassAdapter
    private TelclassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telmsg);
        mListView = (ListView) findViewById(R.id.listview);
        adapter = new TelclassAdapter(this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        //适配数据
        initAppDBFile();
        adapter.clearDataToAdapter();
        adapter.addDataToAdapter(new TelclassInfo("本地电话", 0));//本地电话分类
        try {
            adapter.addDataToAdapter(DBRead.readTeldbClasslist());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//db库内的电话分类
        adapter.notifyDataSetChanged();
    }
    private void initAppDBFile() {
        //检测是否在通讯录大全DB文件
        if (!DBRead.isExistsTeldbFile()) {
            try {
                //将本地项目中的Assets/db/commonnum.db文件复制写出到DBRead.telFile
                AssetsDBManager.copyAssetsFileToFile(
                        getApplicationContext(),
                        "db/commonnum.db", DBRead.telFile);
            } catch (IOException e) {
                ToastUtil.show(this, "初始通讯大全数据库文件异常",
                        Toast.LENGTH_SHORT);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //本地通话
        if (position == 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            startActivity(intent);
            return;
        }
        TelclassInfo classInfo = adapter.getItem(position);
        //跳转至电话浏览页面,且传入idx,并且根据idx跳转
        Intent intent = new Intent(this, TellistActivity.class);
        intent.putExtra("idx", classInfo.idx);
        startActivity(intent);
    }
    private long preTime = 0;
    private long curTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            curTime = System.currentTimeMillis();
            if (curTime - preTime <= 800) {
                finish();
            }
            ToastUtil.show(getApplicationContext(), "再接一次退出",
                    Toast.LENGTH_SHORT);
            preTime = curTime;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
