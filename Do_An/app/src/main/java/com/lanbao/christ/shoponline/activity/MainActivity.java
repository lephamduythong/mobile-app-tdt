package com.lanbao.christ.shoponline.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.adapter.CategoryAdapter;
import com.lanbao.christ.shoponline.adapter.ProductAdapter;
import com.lanbao.christ.shoponline.model.Cart;
import com.lanbao.christ.shoponline.model.Category;
import com.lanbao.christ.shoponline.model.Product;
import com.lanbao.christ.shoponline.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewHome;
    NavigationView navigationView;
    ListView listViewHome;
    DrawerLayout drawerLayout;
    ArrayList<Category> listCate;
    CategoryAdapter categoryAdapter;
    ArrayList<Product> listPro;
    ProductAdapter productAdapter;
    public static ArrayList<Cart> dataCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        ActionBar();
        ActionViewFlipper();
        getDataCategory();
        getDataNewProduct();
        catchOnItemListView();

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
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void catchOnItemListView() {
        listViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this,PhoneActivity.class);
                        intent1.putExtra("idCategory",listCate.get(i).getId());
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this,LaptopActivity.class);
                        intent2.putExtra("idCategory",listCate.get(i).getId());
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this,FashionActivity.class);
                        intent3.putExtra("idCategory",listCate.get(i).getId());
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this,LifeStyleActivity.class);
                        intent4.putExtra("idCategory",listCate.get(i).getId());
                        startActivity(intent4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void getDataCategory(){
        Category cate1 = new Category(1,"Điện thoại",R.drawable.phone);
        Category cate2 = new Category(2,"Laptop",R.drawable.laptop);
        Category cate3 = new Category(3,"Thời trang",R.drawable.shirt);
        Category cate4 = new Category(4,"Đồ gia dụng",R.drawable.giadung);
        listCate.add(cate1); listCate.add(cate2); listCate.add(cate3); listCate.add(cate4);
        categoryAdapter.notifyDataSetChanged();
    }

    private void getDataNewProduct(){
        Product pro1 = new Product(1,"Apple iPhone 7 Plus 32GB", 17190000, R.drawable.apple7plus32, "Hệ điều hành iOS 10\n" +
                "Màn hình 5.5inch LED-backlit IPS LCD 1080 x 1920",1);
        Product pro2 = new Product(2,"Apple iPhone 8 Plus 64GB", 21089000, R.drawable.apple8plus64, "Màn hình Retina HD 5.5 inch với True Tone\n" +
                "Thiết kế hoàn toàn bằng kính và nhôm, chống nước và chống bụi",1);
        Product pro3 = new Product(3,"Apple iPhone X 64GB Space Grey", 25489000, R.drawable.apple1064, "Màn hình Super Retina HD 5.8 inch với HDR và True Tone\n" +
                "Thiết kế hoàn toàn bằng kính và thép không gỉ, chống nước và chống bụi",1);
        Product pro4 = new Product(4,"Apple Macbook Air MMGG2 13.3inch", 23300000, R.drawable.macbookair, "Màn hình 13 inch LED\n" +
                "Bộ vi xử lý Intel Core i5-5250U 1.6GHz Turbo boost nâng xung nhịp lên 2.7 GHz",2);
        Product pro5 = new Product(5,"Apple MacBook Pro 13-inch 2.3GHz dual-core i5 256GB Silver", 33190000, R.drawable.macbookpro, "Màn hình 13inch Retina sắc nét\n" +
                "Intel Core i5 lõi kép thế hệ 7\n" +
                "Đồ họa Intel Iris Plus Graphics 640\n" +
                "Ổ cứng SSD 256GB siêu nhanh",2);
        Product pro6 = new Product(6,"Áo sơ mi Nian Jeep kiểu lính ngắn tay", 229000, R.drawable.shirtlinh, "Áo sơ mi tay dài\n" +
                "Chất liệu vải kaki",3);
        Product pro7 = new Product(7,"Samsung Galaxy S9 Plus 128GB", 24990000, R.drawable.apple7plus32, "Màn hình: Super AMOLED, 6.22\", Quad HD+ (2K+)\n" +
                "Hệ điều hành: Android 8.0 (Oreo)\n" +
                "Camera sau: 12 MP",1);
        listPro.add(pro1);  listPro.add(pro2);  listPro.add(pro3);  listPro.add(pro4);  listPro.add(pro5); listPro.add(pro6); listPro.add(pro7);
        productAdapter.notifyDataSetChanged();

    }

    /*private void getDataCategory(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathCategory, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            nameCategoryPro = jsonObject.getString("name");
                            imageCategoryPro = jsonObject.getString("image");
                            listCate.add(new Category(id, nameCategoryPro, imageCategoryPro));
                            categoryAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }*/

    private void ActionViewFlipper(){
        ArrayList<String> arrquangcao = new ArrayList<>();
        arrquangcao.add("http://laz-img-cdn.alicdn.com/images/ims-web/TB1l0wUdQomBKNjSZFqXXXtqVXa.jpg_1200x1200.jpg");
        arrquangcao.add("http://laz-img-cdn.alicdn.com/images/ims-web/TB1.fUUdRjTBKNjSZFuXXb0HFXa.jpg_1200x1200Q100.jpg_.webp");
        arrquangcao.add("http://laz-img-cdn.alicdn.com/images/ims-web/TB1CEdWdQomBKNjSZFqXXXtqVXa.jpg_1200x1200Q100.jpg_.webp");
        arrquangcao.add("http://laz-img-cdn.alicdn.com/images/ims-web/TB1CEdWdQomBKNjSZFqXXXtqVXa.jpg_1200x1200Q100.jpg_.webp");
        for(int i = 0; i < arrquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(arrquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void initViews(){
        toolbar          = (Toolbar) findViewById(R.id.toolbarHome);
        viewFlipper      = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerViewHome = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView   = (NavigationView)findViewById(R.id.navigationview);
        listViewHome     = (ListView) findViewById(R.id.listviewHome);
        drawerLayout     = (DrawerLayout) findViewById(R.id.drawerlayout);
        listCate         = new ArrayList<>();
        listCate.add(0, new Category(0, "Home", R.drawable.home));
        categoryAdapter  = new CategoryAdapter(listCate, getApplicationContext());
        listViewHome.setAdapter(categoryAdapter);
        listPro          = new ArrayList<>();
        productAdapter   = new ProductAdapter(getApplicationContext(), listPro);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewHome.setAdapter(productAdapter);
        if(dataCart != null){

        }
        else {
            dataCart = new ArrayList<>();
        }
    }
}
