package edu.feicui.com.splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.com.splash.bean.TelclassInfo;

/**
 * Created by ｌ on 2016/5/4.
 */
public class TelclassAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    // 当前适配器内的数据集合 (当前适配器适配工作只认此集合)
    private ArrayList<TelclassInfo> adapterDatas = new ArrayList<>();
    public TelclassAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //当前适配器内的数据集合(当前适配器)


    //添加数据到当前适配器集合
    public void addDataToAdapter(TelclassInfo e) {
        if (e != null) {
            adapterDatas.add(e);
        }
    }
    //添加数据到当前适配器集合
    public void addDataToAdapter(List<TelclassInfo>e){
        if (e!=null){
            adapterDatas.addAll(e);
        }
    }
    public void clearDataToAdapter(){
        adapterDatas.clear();
    }
    public ArrayList<TelclassInfo> getDataFromAdapter(){
        return adapterDatas;
    }

    @Override
    public int getCount() {
        return adapterDatas.size();
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    R.layout.inflate_telmgr_listitem, null);
        }
        TextView tv_text = (TextView) convertView.findViewById(R.id.textview);
        tv_text.setText(getItem(position).name);
        return convertView;
    }
    @Override
    public TelclassInfo getItem(int position) {
        return adapterDatas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
