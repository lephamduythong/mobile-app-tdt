package com.lanbao.christ.shoponline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.model.Cart;
import com.lanbao.christ.shoponline.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailProductActivity extends AppCompatActivity {

    Toolbar toolbarDetail;
    ImageView imageDetail;
    TextView txtName, txtPrice, txtDescription;
    Spinner spinner;
    Button buttonPurchase;
    int id = 0;
    String namedetail = "";
    int price = 0;
    int image = 0;
    String descriptionDetail = "";
    int idCate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        InitViews();
        ActionToolBar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
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
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        buttonPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.dataCart.size() > 0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i = 0; i < MainActivity.dataCart.size(); i++){
                        if(MainActivity.dataCart.get(i).getIdPro() == id){
                            MainActivity.dataCart.get(i).setNumberPro(MainActivity.dataCart.get(i).getNumberPro() + sl);
                            if(MainActivity.dataCart.get(i).getNumberPro() >= 10){
                                MainActivity.dataCart.get(i).setNumberPro(10);
                            }
                            MainActivity.dataCart.get(i).setPricePro(price * MainActivity.dataCart.get(i).getNumberPro());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int number = Integer.parseInt(spinner.getSelectedItem().toString());
                        long newPrice = number * price;
                        MainActivity.dataCart.add(new Cart(id,namedetail,newPrice,image,number));
                    }
                }
                else {
                    int number = Integer.parseInt(spinner.getSelectedItem().toString());
                    long newPrice = number * price;
                    MainActivity.dataCart.add(new Cart(id,namedetail,newPrice,image,number));
                }
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] number = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item, number);
        spinner.setAdapter(arrayadapter);
    }

    private void GetInformation() {
        Product product = (Product) getIntent().getSerializableExtra("detailproduct");
        id = product.getId();
        namedetail = product.getName();
        price = product.getPrice();
        image = product.getImage();
        descriptionDetail = product.getDescription();
        // idCate = product.getIdcate();
        txtName.setText(namedetail);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPrice.setText("Giá : " + decimalFormat.format(product.getPrice()) + " Đ");
        txtDescription.setText(descriptionDetail);
        Picasso.with(getApplicationContext()).load(product.getImage())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imageDetail);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void InitViews(){
        toolbarDetail  = (Toolbar) findViewById(R.id.toolbarDetailPro);
        imageDetail    = (ImageView) findViewById(R.id.imageviewdetailpro);
        txtName        = (TextView) findViewById(R.id.textviewdetailpro);
        txtPrice       = (TextView) findViewById(R.id.textviewpricedetailpro);
        txtDescription = (TextView) findViewById(R.id.textviewdesdetailpro);
        spinner        = (Spinner) findViewById(R.id.spinner);
        buttonPurchase = (Button) findViewById(R.id.buttonPurchase);
    }
}
