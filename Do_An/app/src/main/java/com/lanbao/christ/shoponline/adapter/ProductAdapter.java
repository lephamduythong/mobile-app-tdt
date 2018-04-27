package com.lanbao.christ.shoponline.adapter;

import android.content.Context;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.activity.DetailProductActivity;
import com.lanbao.christ.shoponline.model.Product;
import com.lanbao.christ.shoponline.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {

    Context context;
    ArrayList<Product> arrProduct;

    public ProductAdapter(Context context, ArrayList<Product> arrProduct) {
        this.context = context;
        this.arrProduct = arrProduct;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newproduct,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Product product = arrProduct.get(position);
        holder.txtNamePro.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPricePro.setText("Giá : " + decimalFormat.format(product.getPrice()) + " Đ");
        Picasso.with(context).load(product.getImage())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imageProduct;
        public TextView txtNamePro, txtPricePro;

        public ItemHolder(View itemView){
            super(itemView);
            imageProduct = (ImageView) itemView.findViewById(R.id.imageviewproduct);
            txtNamePro   = (TextView) itemView.findViewById(R.id.textviewproduct);
            txtPricePro  = (TextView) itemView.findViewById(R.id.textviewprice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    Product product =  arrProduct.get(getPosition());
                    intent.putExtra("detailproduct", product);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.showToast_Short(context, arrProduct.get(getPosition()).getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
