package com.lanbao.christ.shoponline.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.adapter.PhoneAdapter;
import com.lanbao.christ.shoponline.model.Product;

import java.util.ArrayList;

public class PhoneActivity extends AppCompatActivity {

    Toolbar toolbarPhone;
    ListView listViewPhone;
    PhoneAdapter phoneAdapter;
    ArrayList<Product> data;
    int idPhone = 0;
    View footerView;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initViews();
        GetIdCategory();
        ActionToolBar();
        GetData();
        LoadMoreData();
        listViewPhone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product) listViewPhone.getItemAtPosition(i);
                Intent intent = new Intent(PhoneActivity.this, DetailProductActivity.class);
                intent.putExtra("detailproduct", product);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menucart:
                Intent intent = new Intent(getApplicationContext(),com.lanbao.christ.shoponline.activity.CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMoreData() {
        listViewPhone.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false) {

                }
            }
        });
    }

    private void GetData() {
        Product pro1 = new Product(1,"Apple iPhone 7 Plus 32GB", 17190000, R.drawable.apple7plus32, "Hệ điều hành iOS 10\n" +
                "Màn hình 5.5inch LED-backlit IPS LCD 1080 x 1920",1);
        Product pro2 = new Product(2,"Apple iPhone 8 Plus 64GB", 21089000, R.drawable.apple8plus64, "Màn hình Retina HD 5.5 inch với True Tone\n" +
                "Thiết kế hoàn toàn bằng kính và nhôm, chống nước và chống bụi",1);
        Product pro3 = new Product(3,"Apple iPhone X 64GB Space Grey", 25489000, R.drawable.apple1064, "Màn hình Super Retina HD 5.8 inch với HDR và True Tone\n" +
                "Thiết kế hoàn toàn bằng kính và thép không gỉ, chống nước và chống bụi",1);
        Product pro4 = new Product(4,"Apple Macbook Air MMGG2 13.3inch", 23300000, R.drawable.macbookair, "Màn hình 13 inch LED\n" +
                "Bộ vi xử lý Intel Core i5-5250U 1.6GHz Turbo boost nâng xung nhịp lên 2.7 GHz",2);
        Product pro5 = new Product(5,"OPPO F3 Plus 4GB 64GB",10690000,R.drawable.oppof3,"Màn hình : 6.0 inch (1080 x 1920 pixels)\n" +
                "Camera : Chính: 16.0 MP, Phụ: Dual 16.0 MP + 8.0 MP\n" +
                "RAM : 4 GB\n" +
                "Bộ nhớ trong : 64 GB",1);
        Product pro6 = new Product(6,"OPPO F7 64GB",7050000,R.drawable.oppof7,"Hệ điều hành:ColorOS 5.0 (Android 8.1)\n" +
                "Camera sau:16 MP\n" +
                "Camera trước:25 MP\n" +
                "CPU:MediaTek Helio P60",1);
        Product pro7 = new Product(7,"Samsung Galaxy S9 Plus 128GB", 24990000, R.drawable.apple7plus32, "Màn hình: Super AMOLED, 6.22\", Quad HD+ (2K+)\n" +
                "Hệ điều hành: Android 8.0 (Oreo)\n" +
                "Camera sau: 12 MP",1);
        data.add(pro1);  data.add(pro2);  data.add(pro3);  data.add(pro4);  data.add(pro5); data.add(pro6); data.add(pro7);
        phoneAdapter.notifyDataSetChanged();
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarPhone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPhone.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIdCategory() {
        idPhone = getIntent().getIntExtra("idCategory",-1);
        Log.d("value cate",idPhone + "");
    }

    private void initViews(){
        toolbarPhone  = (Toolbar) findViewById(R.id.toolbarPhone);
        listViewPhone = (ListView) findViewById(R.id.listviewPhone);
        data     = new ArrayList<>();
        phoneAdapter  = new PhoneAdapter(getApplicationContext(), data);
        listViewPhone.setAdapter(phoneAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        footerView = inflater.inflate(R.layout.progressbar,null);
    }

    public class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
            }
        }
    }

}
