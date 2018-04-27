package com.lanbao.christ.shoponline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanbao.christ.shoponline.R;

public class CustomerInforActivity extends AppCompatActivity {

    EditText edtNameCustomer, edtEmail, edtPhoneNumber;
    Button btnXacNhan, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_infor);
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
                String name  = edtNameCustomer.getText().toString().trim();
                String phone = edtPhoneNumber.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                if(name.length() > 0 && phone.length() > 0 && email.length() > 0){

                }
                else {
                    Toast.makeText(CustomerInforActivity.this,"Hãy kiểm tra lại dữ liệu",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InitViews() {
        edtNameCustomer = (EditText) findViewById(R.id.edittextnamecustomer);
        edtEmail        = (EditText) findViewById(R.id.edittextemail);
        edtPhoneNumber  = (EditText) findViewById(R.id.edittextphonenumber);
        btnXacNhan      = (Button) findViewById(R.id.buttonxacnhan);
        btnReturn       = (Button) findViewById(R.id.buttontrove);
    }
}
