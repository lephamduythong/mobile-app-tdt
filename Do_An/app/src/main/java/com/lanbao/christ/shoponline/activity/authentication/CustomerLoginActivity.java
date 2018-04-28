package com.lanbao.christ.shoponline.activity.authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.activity.MainActivity;
import com.lanbao.christ.shoponline.ultil.GlobalVars;
import com.lanbao.christ.shoponline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerLoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        edtEmail = (EditText) findViewById(R.id.edittextEmailcustomer_login);
        edtPassword = (EditText) findViewById(R.id.edittextPassword_login);

        btnLogin = (Button) findViewById(R.id.buttonxacnhan_login);
        btnReturn = (Button) findViewById(R.id.buttontrove_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void login() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.customerPath, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("TEST", "Get response");
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String email = jsonObject.getString("email");
                            String password = jsonObject.getString("password");
                            Log.d("TEST", email);
                            Log.d("TEST", password);
                            if (email.equals(edtEmail.getText().toString().trim()) && password.equals(edtPassword.getText().toString().trim())) {
                                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                GlobalVars.isLogin = true;
                                GlobalVars.activeEmail = email;
                                finish();
                            } else if ( i == response.length() - 1 ) {
                                Toast.makeText(getApplicationContext(), "Email dăng ký không tồn tại", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TEST", "Error");
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
