package com.lanbao.christ.shoponline.activity.authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.activity.CustomerInforActivity;
import com.lanbao.christ.shoponline.model.ListProduct;
import com.lanbao.christ.shoponline.model.Product;
import com.lanbao.christ.shoponline.ultil.GlobalVars;
import com.lanbao.christ.shoponline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;


public class CustomerRegisterActivity extends AppCompatActivity {

    EditText edtNameCustomer, edtEmail, edtPhoneNumber, edtAddress, edtPassword;
    Button btnXacNhan, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        InitViews();
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        EventButton();
    }

    private void EventButton() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validation information
                String name  = edtNameCustomer.getText().toString().trim();
                String phone = edtPhoneNumber.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
//
//                if(name.length() > 0 && phone.length() > 0 && email.length() > 0){
//
//                }
//                else {
//                    Toast.makeText(CustomerRegisterActivity.this,"Hãy kiểm tra lại dữ liệu",Toast.LENGTH_LONG).show();
//                }

                // Insert customer's data
                Log.d("TEST", "Start request");
                sendData(name, phone, address, email, password);

            }
        });
    }

    private void sendData(String name, String phone, String address, String email,String password) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("phone", phone);
        params.put("address", address);
        params.put("email", email);
        params.put("password", password);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request_json = new JsonObjectRequest(Server.customerPath, new JSONObject(params),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Lỗi gửi dữ liệu", Toast.LENGTH_LONG).show();
                }
        });
        requestQueue.add(request_json);
    }



    private void InitViews() {
        edtNameCustomer = (EditText) findViewById(R.id.edittextnamecustomer_register);
        edtEmail        = (EditText) findViewById(R.id.edittextemail_register);
        edtPhoneNumber  = (EditText) findViewById(R.id.edittextphonenumber_register);
        edtAddress = (EditText) findViewById(R.id.edittextaddress_register);
        edtPassword = (EditText) findViewById(R.id.edittextpassword_register);
        btnXacNhan      = (Button) findViewById(R.id.buttonxacnhan_register);
        btnReturn       = (Button) findViewById(R.id.buttontrove_register);
    }
}
