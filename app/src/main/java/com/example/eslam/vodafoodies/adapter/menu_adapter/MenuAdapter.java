package com.example.eslam.vodafoodies.adapter.menu_adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.activity.MenuActivity;
import com.example.eslam.vodafoodies.model.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Eslam on 10/14/2017.
 */

public class MenuAdapter extends BaseExpandableListAdapter {
    private MenuActivity activity;
    private List<String> titles;
    private Map<String, List<Item>> itemsMap;


    public MenuAdapter(MenuActivity activity, List<String> titles, Map<String, List<Item>> itemsMap) {
        this.activity = activity;
        this.titles = titles;
        this.itemsMap = itemsMap;
        filter();
    }

    private void filter() {
        for (List<Item> list : this.itemsMap.values()) {
            Iterator<Item> itemIterator = list.iterator();
            while (itemIterator.hasNext()) {
                Item item = itemIterator.next();
                if (item.getPrices() == null || item.getPrices().size() == 0) {
                    itemIterator.remove();
                }
            }
        }
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
            LayoutInflater inflater = (LayoutInflater) this.activity
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
            LayoutInflater inflater = (LayoutInflater) this.activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_row, null);
        }
        final Item item = itemsMap.get(titles.get(i)).get(i1);
        TextView itemTitle = view.findViewById(R.id.item_title);
        CheckBox itemCheckBox = view.findViewById(R.id.item_check_box);
        if (item.isSelected()) {
            itemCheckBox.setChecked(true);
        } else {
            itemCheckBox.setChecked(false);
        }
        itemTitle.setText(item.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isSelected()) {
                    item.setSelected(false);
                    activity.removeFromSelectedItemsMap(item.getId());
                    notifyDataSetChanged();
                } else {
                    selectItem(item);
                }
            }
        });
        return view;
    }

    private void selectItem(final Item item) {
        Set<String> keySet = item.getPrices().keySet();
        if (keySet.size() > 1) {
            final ArrayList<String> dialogItems = new ArrayList<>();
            for (String key : keySet) {
                dialogItems.add(key + " - " + item.getPrices().get(key) + " EGP");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(item.getName())
                    .setItems(dialogItems.toArray(new CharSequence[dialogItems.size()]), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String selectedSize = dialogItems.get(i).substring(0, dialogItems.get(i).indexOf(" - "));
                            String itemId = item.getId();
                            activity.addToSelectedItemsMap(itemId, selectedSize);
                            item.setSelected(true);
                            notifyDataSetChanged();
                        }
                    }).show();
        } else {
            activity.addToSelectedItemsMap(keySet.iterator().next(), String.valueOf(item.getPrices().values().iterator().next()));
            item.setSelected(true);
            notifyDataSetChanged();
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public MenuActivity getActivity() {
        return activity;
    }

    public void setActivity(MenuActivity activity) {
        this.activity = activity;
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
        filter();
    }
}
