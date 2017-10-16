package com.example.eslam.vodafoodies.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ExpandableListView;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.adapter.menu_adapter.MenuAdapter;
import com.example.eslam.vodafoodies.model.Category;
import com.example.eslam.vodafoodies.model.Item;
import com.example.eslam.vodafoodies.network.NetworkCallback;
import com.example.eslam.vodafoodies.network.NetworkManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    private MenuAdapter adapter;
    private String venueId;
    private HashMap<String,String> selectedItems = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initViews();
        venueId = getIntent().getExtras().getString("venue_id");
        NetworkManager.getInstance().getVenueMenu(venueId, new NetworkCallback() {
            @Override
            public void onSuccess(Object responseBody) {
                Map<String,Category> responseMap = (Map<String, Category>) responseBody;
                ArrayList<String> titles = new ArrayList<>(responseMap.keySet());
                HashMap<String,List<Item>> itemsMap = new HashMap<>();
                for (Category category : responseMap.values()) {
                    itemsMap.put(category.getName(),new ArrayList<>(category.getItems().values()));
                }
                adapter.setTitles(titles);
                adapter.setItemsMap(itemsMap);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable error) {
                Log.i("MyTag",error.getMessage());
            }
        });

    }

    private void initViews() {
        expandableListView = (ExpandableListView) findViewById(R.id.menu_expandable_list_view);
        adapter = new MenuAdapter(MenuActivity.this, new ArrayList<String>(), new HashMap<String, List<Item>>());
        expandableListView.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void addToSelectedItemsMap(String itemId, String size){
        selectedItems.put(itemId,size);
    }

    public void removeFromSelectedItemsMap(String itemId){
        if(selectedItems.containsKey(itemId))
            selectedItems.remove(itemId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
