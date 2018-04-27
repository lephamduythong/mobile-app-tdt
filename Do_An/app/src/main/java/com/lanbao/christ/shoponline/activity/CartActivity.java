package com.lanbao.christ.shoponline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.adapter.CartAdapter;
import com.lanbao.christ.shoponline.model.Cart;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {

    ListView listViewCart;
    TextView textViewMessage;
    static TextView textViewSumPrice;
    Button btnPay, btnTiepTucMua;
    Toolbar toolbarCart;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        InitViews();
        ActionToolBar();
        CheckData();
        EventUltil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.dataCart.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), CustomerInforActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CartActivity.this, "Giỏ hàng của bạn chưa có sản phẩm để thanh toán", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnTiepTucMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchOnItemListView() {
        listViewCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MainActivity.dataCart.size() <= 0){
                            textViewMessage.setVisibility(View.VISIBLE);
                        }
                        else {
                            MainActivity.dataCart.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(MainActivity.dataCart.size() <= 0){
                                textViewMessage.setVisibility(View.VISIBLE);
                            }
                            else{
                                textViewMessage.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUltil() {
        long tongtien = 0;
        for (int i = 0; i < MainActivity.dataCart.size(); i++){
            tongtien += MainActivity.dataCart.get(i).getPricePro();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewSumPrice.setText(decimalFormat.format(tongtien) + "Đ");
    }

    private void CheckData() {
        if(MainActivity.dataCart.size() <= 0){
            cartAdapter.notifyDataSetChanged();
            textViewMessage.setVisibility(View.VISIBLE);
            listViewCart.setVisibility(View.INVISIBLE);
        }
        else {
            cartAdapter.notifyDataSetChanged();
            textViewMessage.setVisibility(View.INVISIBLE);
            listViewCart.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void InitViews() {
        listViewCart     = (ListView) findViewById(R.id.listviewCart);
        textViewMessage  = (TextView) findViewById(R.id.textviewMessage);
        textViewSumPrice = (TextView) findViewById(R.id.textviewsumprice);
        btnPay           = (Button) findViewById(R.id.buttonPay);
        btnTiepTucMua    = (Button) findViewById(R.id.buttonmuahang);
        toolbarCart      = (Toolbar) findViewById(R.id.toolbarCart);
        cartAdapter      = new CartAdapter(getApplicationContext(),MainActivity.dataCart);
        listViewCart.setAdapter(cartAdapter);
    }
}
