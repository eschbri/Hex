package com.joelsharin.brianesch.hex;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Brian Esch on 4/13/2015.
 * Yeeee Boyyyyy
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> dataHeaders;
    private HashMap<String, List<String>> dataOfChild;

    public ExpandableListAdapter(Context appContext, List<String> listOfHeaders,
                                 HashMap<String, List<String>> dataOfChildren){
        this.context = appContext;
        this.dataHeaders = listOfHeaders;
        this.dataOfChild = dataOfChildren;
    }

    @Override
    public Object getChild(int headerGroup, int childPosition){
        return this.dataOfChild.get(this.dataHeaders.get(headerGroup)).get(childPosition);
    }

    @Override
    public long getChildId(int headerGroup, int childPosition){
        return childPosition;
    }

    @Override
    public View getChildView(int headerGroup, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent){
        // Initialize the text of the child
        final String childText = (String) getChild(headerGroup, childPosition);

        //Safety check if convertView is null
        if(convertView == null ){
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.game_list_item, null);
        }

        //Set text for the child into the
        TextView txtListChild = (TextView) convertView;
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int headerGroup){
        return this.dataOfChild.get(dataHeaders.get(headerGroup)).size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return this.dataHeaders.get(groupPosition);
    }

    @Override
    public int getGroupCount(){
        return this.dataHeaders.size();
    }

    @Override
    public long getGroupId(int headerGroup){
        return headerGroup;
    }

    @Override
    public View getGroupView(int headerGroup, boolean isExpanded, View convertView,
                             ViewGroup parent){
        String headerTitle = (String) getGroup(headerGroup);
        if(convertView == null){
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.game_list_group, null);
        }

        TextView headerName = (TextView) convertView.findViewById(R.id.lblListHeader);
        headerName.setTypeface(null, Typeface.BOLD);
        headerName.setText(headerTitle);

        return convertView;
    }


    @Override
    public boolean hasStableIds(){
        return false;
    }

    @Override
    public boolean isChildSelectable(int headerGroup, int childPosition){
        return true;
    }

}
