package com.example.andrewoh.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class quizListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private ArrayList<quizListItem> data;
    private int layout;

    public quizListAdapter(Context context, int layout, ArrayList<quizListItem> data) {
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
        return data.get(position).getProblem();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }
       
        quizListItem listViewItem = data.get(position);
        TextView problem = (TextView) convertView.findViewById(R.id.problem);
       
        problem.setText(listViewItem.getProblem());
        return convertView;
    }
}
