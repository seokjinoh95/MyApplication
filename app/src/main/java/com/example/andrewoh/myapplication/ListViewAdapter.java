package com.example.andrewoh.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//리스트뷰와 데이터를 연결시킬 어뎁터
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ListViewItem> data;
    private int layout;

    public ListViewAdapter(Context context, int layout, ArrayList<ListViewItem> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //inflater을 통해서 아이템의 구조를 리스트뷰안에 적용시켜준다.
            convertView = inflater.inflate(layout, parent, false);
        }
        //리스트뷰안에 들어가는 아이템을 초기화시켜준다.
        ListViewItem listViewItem = data.get(position);
        TextView name = (TextView) convertView.findViewById(R.id.Name);
        //아이템의 name 섹션에 유저의 이름을 가져온다.
        name.setText(listViewItem.getName());
        TextView score = (TextView) convertView.findViewById(R.id.Score);
        //아이템의 score 섹션에 유저의 점수정보를 가져온다.
        score.setText(listViewItem.getScore());
        return convertView;
    }
}
