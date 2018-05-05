package com.lanbao.christ.shoponline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.adapter.CartAdapter;
import com.lanbao.christ.shoponline.model.Cart;
import com.lanbao.christ.shoponline.ultil.GlobalVars;
import com.lanbao.christ.shoponline.ultil.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CustomerInforActivity extends AppCompatActivity {

    Button btnXacNhan, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_infor);
        InitViews();
        EventButton();
    }

    private void EventButton() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "Đã thêm vào đơn hàng của bạn", Toast.LENGTH_LONG).show();

            ArrayList<Cart> list = CartAdapter.getItems();
            Log.d("TEST", list.get(0).getNamePro());



            // Insert new bill
            HashMap<String, String> params = new HashMap<String, String>();
            SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
            String currentDate = s.format(new Date());
            params.put("dateCreated", currentDate);
            params.put("status", "0");
            params.put("customerId", String.valueOf(GlobalVars.activeUserId));



            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request_json = new JsonObjectRequest(Server.billPath, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("TEST", "Return bill id: " + response.getString("id"));
                                String billId = response.getString("id");
                                ArrayList<Cart> data = new ArrayList<Cart>(CartAdapter.getItems());
                                addNewBillDetails(data, billId);
                                CartAdapter.clearCart();
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Lỗi gửi dữ liệu", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(request_json);




            // Insert new bill details


            // Return to main activity
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addNewBillDetails(ArrayList<Cart> data, String billId) {
        Log.d("TEST", "Start add new bill details");
        Log.d("TEST", "Size" + data.size());
        HashMap<String, String> params = new HashMap<String, String>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        for (int i = 0; i < data.size(); i++) {
            params.clear();
            params.put("billId", billId);
            params.put("productId", String.valueOf(CartAdapter.getItems().get(i).getIdPro()));
            params.put("quantity", String.valueOf(CartAdapter.getItems().get(i).getNumberPro()));

            JsonObjectRequest request_json = new JsonObjectRequest(Server.billDetailPath, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("TEST", "Done 1 bill details");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Lỗi gửi dữ liệu", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(request_json);
        }
    }

    private void InitViews() {
        btnXacNhan      = (Button) findViewById(R.id.buttonxacnhan);
        btnReturn       = (Button) findViewById(R.id.buttontrove);
    }
}
