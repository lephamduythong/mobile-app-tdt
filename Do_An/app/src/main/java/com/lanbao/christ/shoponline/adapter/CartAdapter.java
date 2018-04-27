package com.lanbao.christ.shoponline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.activity.CartActivity;
import com.lanbao.christ.shoponline.activity.MainActivity;
import com.lanbao.christ.shoponline.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    ArrayList<Cart> arrCart;
    ViewHolder viewHolder = null;

    public CartAdapter(Context context, ArrayList<Cart> arrCart) {
        this.context = context;
        this.arrCart = arrCart;
    }

    @Override
    public int getCount() {
        return arrCart.size();
    }

    @Override
    public Object getItem(int i) {
        return arrCart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtNameCart, txtPriceCart;
        public ImageView imageCart;
        public Button btnMinus, btnValues, btnPlus;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_cart,null);
            viewHolder.txtNameCart  = (TextView) view.findViewById(R.id.textviewnamecart);
            viewHolder.txtPriceCart = (TextView) view.findViewById(R.id.textviewpricecart);
            viewHolder.imageCart    = (ImageView) view.findViewById(R.id.imageviewCart);
            viewHolder.btnMinus     = (Button) view.findViewById(R.id.buttonminus);
            viewHolder.btnValues    = (Button) view.findViewById(R.id.buttonvalues);
            viewHolder.btnPlus      = (Button) view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Cart cart = (Cart) getItem(i);
        viewHolder.txtNameCart.setText(cart.getNamePro());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPriceCart.setText(decimalFormat.format(cart.getPricePro()) + " Đ");
        Picasso.with(context).load(cart.getImagePro())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imageCart);
        viewHolder.btnValues.setText(cart.getNumberPro() + "");
        int sl = Integer.parseInt(viewHolder.btnValues.getText().toString());
        if(sl >= 10){
            viewHolder.btnPlus.setVisibility(View.INVISIBLE);
            viewHolder.btnMinus.setVisibility(View.VISIBLE);
        }
        else if(sl <= 1){
            viewHolder.btnMinus.setVisibility(View.INVISIBLE);
        }
        else if(sl >= 1){
            viewHolder.btnPlus.setVisibility(View.VISIBLE);
            viewHolder.btnMinus.setVisibility(View.VISIBLE);
        }
        viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder v = viewHolder;
                int slmoinhat = Integer.parseInt(viewHolder.btnValues.getText().toString()) + 1;
                int slht      = MainActivity.dataCart.get(i).getNumberPro();
                long priceht  = MainActivity.dataCart.get(i).getPricePro();
                MainActivity.dataCart.get(i).setNumberPro(slmoinhat);
                long pricemoinhat = (priceht * slmoinhat) / slht;
                MainActivity.dataCart.get(i).setPricePro(pricemoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtPriceCart.setText(decimalFormat.format(pricemoinhat) + " Đ");
                CartActivity.EventUltil();
                if(slmoinhat > 9){
                    viewHolder.btnPlus.setVisibility(View.INVISIBLE);
                    viewHolder.btnMinus.setVisibility(View.VISIBLE);
                    viewHolder.btnValues.setText(String.valueOf(slmoinhat));
                }
                else {
                    viewHolder.btnPlus.setVisibility(View.VISIBLE);
                    viewHolder.btnMinus.setVisibility(View.VISIBLE);
                    viewHolder.btnValues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(viewHolder.btnValues.getText().toString()) - 1;
                int slht = MainActivity.dataCart.get(i).getNumberPro();
                long priceht = MainActivity.dataCart.get(i).getPricePro();
                MainActivity.dataCart.get(i).setNumberPro(slmoinhat);
                long pricemoinhat = (priceht * slmoinhat) / slht;
                MainActivity.dataCart.get(i).setPricePro(pricemoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtPriceCart.setText(decimalFormat.format(pricemoinhat) + " Đ");
                CartActivity.EventUltil();
                if (slmoinhat < 2) {
                    viewHolder.btnMinus.setVisibility(View.INVISIBLE);
                    viewHolder.btnPlus.setVisibility(View.VISIBLE);
                    viewHolder.btnValues.setText(String.valueOf(slmoinhat));
                } else {
                    viewHolder.btnPlus.setVisibility(View.VISIBLE);
                    viewHolder.btnMinus.setVisibility(View.VISIBLE);
                    viewHolder.btnValues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
