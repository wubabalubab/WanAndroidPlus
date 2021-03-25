package com.example.kotlintestdemo.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.kotlintestdemo.R;
import com.example.kotlintestdemo.bean.JRBean.TixiBean;

import java.util.List;

public class FgProjectExpandableAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private List<TixiBean> list;

    public FgProjectExpandableAdapter(Activity activity, List<TixiBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildren()==null?0:list.get(groupPosition).getChildren().size();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_expandablelist_parent, null);
            viewHolder1 = new ViewHolder1(convertView);
            convertView.setTag(viewHolder1);
        } else {
            viewHolder1= (ViewHolder1) convertView.getTag();
        }
        viewHolder1.onename.setText(list.get(groupPosition).getName());
        return convertView;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder2 viewHolder2;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_expandablelist_child, null);
            viewHolder2 = new ViewHolder2(convertView);
            convertView.setTag(viewHolder2);
        } else {
            viewHolder2= (ViewHolder2) convertView.getTag();
        }
        viewHolder2.childname.setText(list.get(groupPosition).getChildren().get(childPosition).getName());

        return convertView;
    }

    public static class ViewHolder1{
        private final TextView onename;

        public ViewHolder1(View itemView) {
            this.onename = itemView.findViewById(R.id.tv_expandableparent);
        }
    }
    public static class ViewHolder2{
        private final TextView childname;

        public ViewHolder2(View itemView) {
            this.childname = itemView.findViewById(R.id.tv_expandablechild);
        }
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
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
