package com.lanbao.christ.shoponline.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.model.Bill;
import com.lanbao.christ.shoponline.ultil.FetchData;
import com.lanbao.christ.shoponline.ultil.GlobalVars;
import com.lanbao.christ.shoponline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillAdapter extends BaseAdapter {
    Context context;
    ArrayList<Bill> arraybill;

    public BillAdapter(Context context, ArrayList<Bill> arraybill) {
        this.context = context;
        this.arraybill = arraybill;
    }

    @Override
    public int getCount() {
        return arraybill.size();
    }

    @Override
    public Object getItem(int i) {
        return arraybill.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtNamePro, txtDate, txtStatus, txtListItems;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_bill,null);
            viewHolder.txtNamePro   = (TextView) view.findViewById(R.id.textviewproductinbill);
            viewHolder.txtDate      = (TextView) view.findViewById(R.id.textviewdate);
            viewHolder.txtStatus    = (TextView) view.findViewById(R.id.textviewstatus);
            viewHolder.txtListItems = (TextView) view.findViewById(R.id.textviewlistitems);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Bill bill = (Bill) getItem(i);
        viewHolder.txtNamePro.setText(
                Html.fromHtml("<i style=\"color: red\">Mã đơn hàng: " + Integer.toString(bill.getIdpro()) + "</i>")
        );

        Log.d("TEST", bill.getDate());

        viewHolder.txtDate.setText("Ngày tạo: " + bill.getDate());

        String strStatus = "Không xác định";
        if (bill.getStatus() == 0) {
            strStatus = "Đang trong quá trình xác nhận";
        } else if (bill.getStatus() == 1) {
            strStatus = "Đã xác nhận đơn hàng";
        } else if (bill.getStatus() == 2) {
            strStatus = "Đang giao hàng";
        } else if (bill.getStatus() == 3) {
            strStatus = "Đã giao hàng";
        }

        viewHolder.txtStatus.setText("Trang thái: " + strStatus);

        String productData = "";
        String[] productList = bill.getProductData().split(";");

        for (int k = 0; k < productList.length; k++) {
            productData += "   + " + productList[k] + "\n";
        }

        viewHolder.txtListItems.setText(productData);

        return view;
    }
}

// @SuppressLint("SimpleDateFormat")
//        DateFormat formatter = new SimpleDateFormat("hh:mm:ss_dd-MM-yyyy");
//        Date date = null;
//        try {
//            date = formatter.parse(bill.getDate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Log.d("TEST", String.valueOf(date.getDate()));
//        Log.d("TEST", String.valueOf(date.getMonth()));
//        Log.d("TEST", String.valueOf(date.getYear()));

//        viewHolder.txtDate.setText("Ngày tạo: " + date.getDay() + "/" + date.getMonth() + "/" + date.getYear()
//                + " " + date.getHours() + ":" + date.getMinutes());