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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.activity.authentication.CustomerLoginActivity;
import com.lanbao.christ.shoponline.activity.authentication.CustomerRegisterActivity;
import com.lanbao.christ.shoponline.adapter.CategoryAdapter;
import com.lanbao.christ.shoponline.adapter.ProductAdapter;
import com.lanbao.christ.shoponline.model.Cart;
import com.lanbao.christ.shoponline.model.Category;
import com.lanbao.christ.shoponline.model.Product;
import com.lanbao.christ.shoponline.ultil.CheckConnection;
import com.lanbao.christ.shoponline.ultil.GlobalVars;
import com.lanbao.christ.shoponline.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    Category loginCate;
    Category registerCate;
    Category logoutCate;
    Category billCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GlobalVars();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            initViews();
            ActionBar();
            ActionViewFlipper();
            getDataCategory();
            getDataNewProduct();
            catchOnItemListView();
        } else {
            CheckConnection.showToast_Short(getApplicationContext(), "Hãy kiểm tra lại kết nối");
            finish();
        }
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

    // private static final int LOGIN_ACTIVITY_REQUEST_CODE = 0;

    private void catchOnItemListView() {
        listViewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Category category = (Category) listViewHome.getAdapter().getItem(i);
                int id = category.getId();

                switch (id){
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
//                    case 3:
//                        Intent intent3 = new Intent(MainActivity.this,FashionActivity.class);
//                        intent3.putExtra("idCategory",listCate.get(i).getId());
//                        startActivity(intent3);
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//                    case 4:
//                        Log.d("TEST","Show me Do gia dung");
//                        Intent intent4 = new Intent(MainActivity.this,LifeStyleActivity.class);
//                        intent4.putExtra("idCategory",listCate.get(i).getId());
//                        startActivity(intent4);
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;

                    case 1000: // Login
                        Intent intent1000 = new Intent(MainActivity.this, CustomerLoginActivity.class);
                        startActivity(intent1000);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        // intent1000.putExtra("idCategory",listCate.get(i).getId());
                        break;
                    case 1001: // Register
                        Log.d("TEST","Register");
                        Intent intent1001 = new Intent(MainActivity.this, CustomerRegisterActivity.class);
                        // intent1001.putExtra("idCategory",listCate.get(i).getId());
                        startActivity(intent1001);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1002: // Logout
                        GlobalVars.isLogin = false;
                        GlobalVars.activeUserId = -1;
                        Toast.makeText(getApplicationContext(), "Đăng xuất thành công", Toast.LENGTH_LONG).show();
                        onResume();
                        // refreshLogout();
                        break;
                    case 1003: // Bills board
                        Intent intent1003 = new Intent(MainActivity.this,BillActivity.class);
                        startActivity(intent1003);
                        break;
                }
            }
        });
    }

    private void getDataCategory(){
        Category cate1 = new Category(1,"Điện thoại",R.drawable.phone);
        Category cate2 = new Category(2,"Laptop",R.drawable.laptop);
//        Category cate3 = new Category(3,"Thời trang",R.drawable.shirt);
//        Category cate4 = new Category(4,"Đồ gia dụng",R.drawable.giadung);

        listCate.add(cate1);
        listCate.add(cate2);



//        listCate.add(cate3);
//        listCate.add(cate4);
//        Log.d("TEST", "Take me to your");

//        if (GlobalVars.isLogin) {
//            Log.d("TEST", "heart");
//            logoutCate = new Category(1002, "Đăng xuất", R.drawable.logout);
//            billCate = new Category(1003, "Đơn hàng", R.drawable.cart);
//            listCate.add(billCate);
//            listCate.add(logoutCate);
//            categoryAdapter.notifyDataSetChanged();
//        } else {
//            Log.d("TEST", "shit");
//            loginCate = new Category(1000, "Đăng nhập", R.drawable.login);
//            registerCate = new Category(1001, "Đăng ký", R.drawable.register);
//            listCate.add(loginCate);
//            listCate.add(registerCate);
//        }

        categoryAdapter.notifyDataSetChanged();
    }

    private void getDataNewProduct(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.productPath + "GetNewProducts", new com.android.volley.Response.Listener<JSONArray>() {
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
                                    R.drawable.apple7plus32,
                                    jsonObject.getString("description"),
                                    jsonObject.getInt("categoryId"));

                            listPro.add(newProduct);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    productAdapter.notifyDataSetChanged();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TEST", "Error");
            }
        });
        requestQueue.add(jsonArrayRequest);

//        Product pro1 = new Product(1,"Apple iPhone 7 Plus 32GB", 17190000, R.drawable.apple7plus32, "Hệ điều hành iOS 10\n" +
//                "Màn hình 5.5inch LED-backlit IPS LCD 1080 x 1920",1);
//        Product pro2 = new Product(2,"Apple iPhone 8 Plus 64GB", 21089000, R.drawable.apple8plus64, "Màn hình Retina HD 5.5 inch với True Tone\n" +
//                "Thiết kế hoàn toàn bằng kính và nhôm, chống nước và chống bụi",1);
//        Product pro3 = new Product(3,"Apple iPhone X 64GB Space Grey", 25489000, R.drawable.apple1064, "Màn hình Super Retina HD 5.8 inch với HDR và True Tone\n" +
//                "Thiết kế hoàn toàn bằng kính và thép không gỉ, chống nước và chống bụi",1);
//        Product pro4 = new Product(4,"Apple Macbook Air MMGG2 13.3inch", 23300000, R.drawable.macbookair, "Màn hình 13 inch LED\n" +
//                "Bộ vi xử lý Intel Core i5-5250U 1.6GHz Turbo boost nâng xung nhịp lên 2.7 GHz",2);
//        Product pro5 = new Product(5,"Apple MacBook Pro 13-inch 2.3GHz dual-core i5 256GB Silver", 33190000, R.drawable.macbookpro, "Màn hình 13inch Retina sắc nét\n" +
//                "Intel Core i5 lõi kép thế hệ 7\n" +
//                "Đồ họa Intel Iris Plus Graphics 640\n" +
//                "Ổ cứng SSD 256GB siêu nhanh",2);
//        Product pro6 = new Product(6,"Áo sơ mi Nian Jeep kiểu lính ngắn tay", 229000, R.drawable.shirtlinh, "Áo sơ mi tay dài\n" +
//                "Chất liệu vải kaki",3);
//        Product pro7 = new Product(7,"Samsung Galaxy S9 Plus 128GB", 24990000, R.drawable.apple7plus32, "Màn hình: Super AMOLED, 6.22\", Quad HD+ (2K+)\n" +
//                "Hệ điều hành: Android 8.0 (Oreo)\n" +
//                "Camera sau: 12 MP",1);
//        listPro.add(pro1);  listPro.add(pro2);  listPro.add(pro3);  listPro.add(pro4);  listPro.add(pro5); listPro.add(pro6); listPro.add(pro7);
//        productAdapter.notifyDataSetChanged();
    }

    private void refreshLogout() {
        listCate.remove(billCate);
        listCate.remove(logoutCate);
        listCate.add(loginCate);
        listCate.add(registerCate);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (GlobalVars.isLogin) {
            Log.d("TEST","LOGOUT Change me");
            if (billCate == null) {
                logoutCate = new Category(1002, "Đăng xuất", R.drawable.logout);
                billCate = new Category(1003, "Đơn hàng", R.drawable.cart);
                listCate.add(billCate);
                listCate.add(logoutCate);
            } else if (!listCate.contains(billCate)) {
                listCate.add(billCate);
                listCate.add(logoutCate);
            }
            listCate.remove(loginCate);
            listCate.remove(registerCate);
            categoryAdapter.notifyDataSetChanged();
        } else {
            Log.d("TEST","LOGIN Change me");
            if (loginCate == null) {
                loginCate = new Category(1000, "Đăng nhập", R.drawable.login);
                registerCate = new Category(1001, "Đăng ký", R.drawable.register);
                listCate.add(loginCate);
                listCate.add(registerCate);
            }
            else if (!listCate.contains(loginCate)) {
                listCate.add(loginCate);
                listCate.add(registerCate);
            }
            listCate.remove(billCate);
            listCate.remove(logoutCate);
            categoryAdapter.notifyDataSetChanged();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == LOGIN_ACTIVITY_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                listCate.add(billCate);
//                listCate.add(logoutCate);
//                listCate.remove(loginCate);
//                listCate.remove(registerCate);
//                categoryAdapter.notifyDataSetChanged();
//            }
//        }
//    }

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
