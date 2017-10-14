package com.example.eslam.vodafoodies.adapter.menu_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.model.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by Eslam on 10/14/2017.
 */

public class MenuAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> titles;
    private Map<String, List<Item>> itemsMap;

    public MenuAdapter(Context context, List<String> titles, Map<String, List<Item>> itemsMap) {
        this.context = context;
        this.titles = titles;
        this.itemsMap = itemsMap;
    }

    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return itemsMap.get(titles.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return titles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return itemsMap.get(titles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.category_row, null);
        }
        TextView categoryTitle = view.findViewById(R.id.category_title);
        categoryTitle.setText(titles.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_row, null);
        }
        TextView itemTitle = view.findViewById(R.id.item_title);
        itemTitle.setText(itemsMap.get(titles.get(i)).get(i1).getName());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public Map<String, List<Item>> getItemsMap() {
        return itemsMap;
    }

    public void setItemsMap(Map<String, List<Item>> itemsMap) {
        this.itemsMap = itemsMap;
    }
}
