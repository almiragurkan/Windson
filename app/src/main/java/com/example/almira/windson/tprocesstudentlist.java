package com.example.almira.windson;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class tprocesstudentlist extends BaseExpandableListAdapter {
    static String[] listname = {"   Students Lists"};
    static String[] studentsLists = {"Student1","Student2","Student3","Student4","Student5"};

    private Context a;
    public tprocesstudentlist(Context a){
        this.a = a;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getGroupCount() {
        return listname.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return studentsLists[groupPosition].length();
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
        TextView tv1 = new TextView(a);
        tv1.setText(listname[groupPosition]);
        tv1.setTextSize(16);
        tv1.setPadding(40,10,10,10);
        return tv1;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView tv2 = new TextView(a);
        tv2.setText(studentsLists[childPosition]);
        tv2.setTextSize(16);
        tv2.setPadding(40,10,10,10);
        return tv2;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

