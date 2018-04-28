package com.lanbao.christ.shoponline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanbao.christ.shoponline.R;

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

            // Insert new bill
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void InitViews() {
        btnXacNhan      = (Button) findViewById(R.id.buttonxacnhan);
        btnReturn       = (Button) findViewById(R.id.buttontrove);
    }
}
