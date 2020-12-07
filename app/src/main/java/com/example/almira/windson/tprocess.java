package com.example.almira.windson;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class tprocess extends BaseExpandableListAdapter {
    String[] mycourse2 = {"   My Courses"};
    static String[] courseLists2 = {"Mobile Application and Development","Digital Logic Design","Python","Java","Sql"};


    private Context c;
    public tprocess(Context c){
        this.c = c;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public int getGroupCount() {
        return mycourse2.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return courseLists2[groupPosition].length();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView tv1 = new TextView(c);
        tv1.setText(mycourse2[groupPosition]);
        tv1.setTextSize(16);
        tv1.setPadding(40,10,10,10);
        return tv1;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView tv2 = new TextView(c);
        tv2.setText(courseLists2[childPosition]);
        tv2.setTextSize(16);
        tv2.setPadding(40,10,10,10);
        return tv2;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
