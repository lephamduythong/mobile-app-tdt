package com.lanbao.christ.shoponline.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.model.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PhoneAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayPhone;

    public PhoneAdapter(Context context, ArrayList<Product> arrayPhone) {
        this.context = context;
        this.arrayPhone = arrayPhone;
    }

    @Override
    public int getCount() {
        return arrayPhone.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayPhone.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtNamePhone, txtPricePhone, txtDescription;
        public ImageView imagePhone;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_phone,null);
            viewHolder.txtNamePhone   = (TextView) view.findViewById(R.id.textviewphone);
            viewHolder.txtPricePhone  = (TextView) view.findViewById(R.id.textviewpricephone);
            viewHolder.txtDescription = (TextView) view.findViewById(R.id.textviewdesphone);
            viewHolder.imagePhone     = (ImageView) view.findViewById(R.id.imageviewphone);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtNamePhone.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPricePhone.setText("Giá : " + decimalFormat.format(product.getPrice()) + " Đ");
        viewHolder.txtDescription.setMaxLines(2);
        viewHolder.txtDescription.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDescription.setText(product.getDescription());
//        Picasso.with(context).load(product.getImage())
//                .placeholder(R.drawable.noimage)
//                .error(R.drawable.error)
//                .into(viewHolder.imagePhone);
        Picasso.with(context).load(product.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.imagePhone, new Callback() {
                    @Override
                    public void onSuccess() {

                    }
                    @Override
                    public void onError() {

                    }
                });
        return view;
    }
}
