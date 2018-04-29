package com.lanbao.christ.shoponline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.adapter.BillAdapter;
import com.lanbao.christ.shoponline.adapter.PhoneAdapter;
import com.lanbao.christ.shoponline.model.Bill;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;
    BillAdapter adapter;
    ArrayList<Bill> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        InitViews();
        ActionToolBar();
        GetData();
    }

    private void GetData() {
        Bill bill1 = new Bill(1,2,"24/4/2018",2);
        Bill bill2 = new Bill(2,2,"24/4/2018",2);
        Bill bill3 = new Bill(3,2,"24/4/2018",2);
        Bill bill4 = new Bill(1,2,"24/4/2018",2);
        Bill bill5 = new Bill(2,2,"24/4/2018",2);
        Bill bill6 = new Bill(2,2,"24/4/2018",2);
        data.add(bill1); data.add(bill2); data.add(bill3); data.add(bill4); data.add(bill5); data.add(bill6);
        adapter.notifyDataSetChanged();
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void InitViews() {
        toolbar  = (Toolbar) findViewById(R.id.toolbarBill);
        listView = (ListView) findViewById(R.id.listviewbill);
        data     = new ArrayList<>();
        adapter  = new BillAdapter(getApplicationContext(), data);
        listView.setAdapter(adapter);
    }
}
