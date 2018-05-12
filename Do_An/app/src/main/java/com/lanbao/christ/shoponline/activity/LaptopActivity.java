package com.lanbao.christ.shoponline.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.adapter.LaptopAdapter;
import com.lanbao.christ.shoponline.adapter.PhoneAdapter;
import com.lanbao.christ.shoponline.model.Product;
import com.lanbao.christ.shoponline.ultil.CheckConnection;
import com.lanbao.christ.shoponline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LaptopActivity extends AppCompatActivity {

    Toolbar toolbarLaptop;
    ListView listViewLaptop;
    LaptopAdapter laptopAdapter;
    ArrayList<Product> data;
    int idLaptop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        initViews();
        GetIdCategory();
        ActionToolBar();
        GetData();
        listViewLaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = (Product) listViewLaptop.getItemAtPosition(i);
                Intent intent = new Intent(LaptopActivity.this, DetailProductActivity.class);
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

    private void GetData() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.productPath + "GetByCategory/2", new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TEST", "Get response");
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);

                            Product newProduct = new Product(
                                    jsonObject.getInt("id"),
                                    jsonObject.getString("name"),
                                    jsonObject.getInt("price"),
                                    jsonObject.getString("image"),
                                    jsonObject.getString("description"),
                                    jsonObject.getInt("categoryId"));

                            data.add(newProduct);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    laptopAdapter.notifyDataSetChanged();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TEST", "Error");
            }
        });
        requestQueue.add(jsonArrayRequest);

//        Product pro1 = new Product(1,"Apple Macbook Air MMGG2 13.3inch", 23300000, R.drawable.macbookair, "Màn hình 13 inch LED\n" +
//                "Bộ vi xử lý Intel Core i5-5250U 1.6GHz Turbo boost nâng xung nhịp lên 2.7 GHz",2);
//        Product pro2 = new Product(2,"Apple MacBook Pro dual-core i5 256GB", 33190000, R.drawable.macbookpro, "Màn hình 13inch Retina sắc nét\n" +
//                "Intel Core i5 lõi kép thế hệ 7\n" +
//                "Đồ họa Intel Iris Plus Graphics 640\n" +
//                "Ổ cứng SSD 256GB siêu nhanh",2);
//        Product pro3 = new Product(3,"Laptop Dell Vostro 5568 i7 7500U", 18790000, R.drawable.dellvostro, "CPU:Intel® Core™ i7 -7500U (2.70 GHz, 4M Cache, up to 3.50 GHz)RAM:8GB DDr4 Bus 2133Mhz (2 Slot, 8GB x 01)Ổ cứng:1TB (1000GB) 5400rpm Hard Drive Sata (Support SSD M.2.Sata)Màn hình:15.6 inch FHD (1920x1080) Anti Glare LED Backlit ",2);
//        Product pro4 = new Product(4,"Laptop Dell Inspiron N7570 128GB SSD", 21190000, R.drawable.dellinspiron, "Mỏng hơn, nhanh hơn, mạnh hơn\n" +
//                "Vỏ kim loại màu bạc cực sang trọng",2);
//        Product pro5 = new Product(5,"Laptop Asus GL552VX-DM143D",18950000,R.drawable.asusgl,"Độ bền cao\n" +
//                "Tiện dụng, hiệu quả",2);
//        Product pro6 = new Product(6,"OPPO F7 64GB",7050000,R.drawable.oppof7,"Hệ điều hành:ColorOS 5.0 (Android 8.1)\n" +
//                "Camera sau:16 MP\n" +
//                "Camera trước:25 MP\n" +
//                "CPU:MediaTek Helio P60",2);
//        Product pro7 = new Product(7,"Samsung Galaxy S9 Plus 128GB", 24990000, R.drawable.apple7plus32, "Màn hình: Super AMOLED, 6.22\", Quad HD+ (2K+)\n" +
//                "Hệ điều hành: Android 8.0 (Oreo)\n" +
//                "Camera sau: 12 MP",2);
//        data.add(pro1);  data.add(pro2);  data.add(pro3);  data.add(pro4);  data.add(pro5); data.add(pro6); data.add(pro7);
//        laptopAdapter.notifyDataSetChanged();
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarLaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIdCategory() {
        idLaptop = getIntent().getIntExtra("idCategory",-1);
        Log.d("value cate",idLaptop + "");
    }

    private void initViews(){
        toolbarLaptop  = (Toolbar) findViewById(R.id.toolbarLaptop);
        listViewLaptop = (ListView) findViewById(R.id.listviewLaptop);
        data     = new ArrayList<>();
        laptopAdapter  = new LaptopAdapter(getApplicationContext(), data);
        listViewLaptop.setAdapter(laptopAdapter);
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
            }
        }
    }
}
