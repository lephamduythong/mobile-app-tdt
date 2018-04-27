package com.lanbao.christ.shoponline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.activity.DetailProductActivity;
import com.lanbao.christ.shoponline.model.Product;
import com.lanbao.christ.shoponline.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayLaptop;

    public LaptopAdapter(Context context, ArrayList<Product> arrayLaptop) {
        this.context = context;
        this.arrayLaptop = arrayLaptop;
    }

    @Override
    public int getCount() {
        return arrayLaptop.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayLaptop.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtNameLaptop, txtPriceLaptop, txtDescription;
        public ImageView imageLaptop;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new LaptopAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_laptop,null);
            viewHolder.txtNameLaptop   = (TextView) view.findViewById(R.id.textviewlaptop);
            viewHolder.txtPriceLaptop  = (TextView) view.findViewById(R.id.textviewpricelaptop);
            viewHolder.txtDescription  = (TextView) view.findViewById(R.id.textviewdeslaptop);
            viewHolder.imageLaptop  = (ImageView) view.findViewById(R.id.imageviewlaptop);
            view.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Product product = (Product) getItem(i);
        viewHolder.txtNameLaptop.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPriceLaptop.setText("Giá : " + decimalFormat.format(product.getPrice()) + " Đ");
        viewHolder.txtDescription.setMaxLines(2);
        viewHolder.txtDescription.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtDescription.setText(product.getDescription());
        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imageLaptop);

        return view;
    }

}
