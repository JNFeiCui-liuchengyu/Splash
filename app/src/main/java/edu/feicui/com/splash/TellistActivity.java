package edu.feicui.com.splash;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.feicui.com.splash.db.DBRead;

public class TellistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ListView mListView;
    private TelnumberAdapter adapter;
    private int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tellist);
        idx = getIntent().getIntExtra("idx", -1);
        //初始控件
        mListView = (ListView) findViewById(R.id.listview);
        adapter = new TelnumberAdapter(this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        try {
            adapter.addDataToAdapter(DBRead.readTeldbTable(idx));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String name = adapter.getItem(position).name;
        String number = adapter.getItem(position).number;
        showCallDialog(name, number);
    }



    private void showCallDialog(final String name, final String number) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("警告").setMessage("是否开始拨打" + name + "电话?\n\nTEL:" + number)
//                .setNegativeButton("取消", null).setPositiveButton("拨号",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(Intent.ACTION_CALL);
//                        intent.setData(Uri.parse("tel://" + number));
//                        startActivity(intent);
//                    }
//                });
        builder.setTitle("警告");
        builder.setMessage("是否开始拨打" + name + "电话 ? \n\nTEL：" + number);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("拨号", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 电话拨打
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel://" + number));
                startActivity(intent);
            }
        });
        builder.show();
    }

}
