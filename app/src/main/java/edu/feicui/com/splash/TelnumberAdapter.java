package edu.feicui.com.splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.com.splash.bean.TelnumberInfo;

/**
 * Created by ｌ on 2016/5/4.
 */
public class TelnumberAdapter extends BaseAdapter{
    private LayoutInflater mLayoutInflater;
    private ArrayList<TelnumberInfo> adapterDatas = new ArrayList<TelnumberInfo>();
    public TelnumberAdapter(Context context){
        mLayoutInflater= (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addDataToAdapter(TelnumberInfo e){
        if (e!=null){
            adapterDatas.add(e);
        }
    }
    public void addDataToAdapter(List<TelnumberInfo>e){
        if (e!=null){
            adapterDatas.addAll(e);
        }
    }
    // 添加数据到当前适配器集合
    public void replaceDataToAdapter(List<TelnumberInfo> e) {
        if (e != null) {
            adapterDatas.clear();
            adapterDatas.addAll(e);
        }
    }
    public ArrayList<TelnumberInfo> getDataFromAdapter() {
        return adapterDatas;
    }
    @Override
    public int getCount() {
        return adapterDatas.size();
    }

    @Override
    public TelnumberInfo getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(
                    R.layout.inflate_telmgr_listitem, null);
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_number = (TextView) convertView
                .findViewById(R.id.tv_number);
        tv_name.setText(getItem(position).name);
        tv_number.setText(getItem(position).number + "");
        System.out.println(getItem(position).name + getItem(position).number);
        return convertView;
    }
}
