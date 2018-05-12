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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    static ArrayList<Cart> arrCart;

    public CartAdapter(Context context, ArrayList<Cart> arrCart) {
        this.context = context;
        this.arrCart = arrCart;
    }

    public static ArrayList<Cart> getItems() {
        return arrCart;
    }

    public static void clearCart() {
        arrCart.clear();
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

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_cart,null);
        TextView txtNameCart  = (TextView) view.findViewById(R.id.textviewnamecart);
        final TextView txtPriceCart = (TextView) view.findViewById(R.id.textviewpricecart);
        ImageView imageCart    = (ImageView) view.findViewById(R.id.imageviewCart);
        final Button btnMinus     = (Button) view.findViewById(R.id.buttonminus);
        final Button btnValues    = (Button) view.findViewById(R.id.buttonvalues);
        final Button btnPlus      = (Button) view.findViewById(R.id.buttonplus);

        Cart cart = (Cart) getItem(i);
        txtNameCart.setText(cart.getNamePro());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtPriceCart.setText(decimalFormat.format(cart.getPricePro()) + " Đ");

        Picasso.with(context).load(cart.getImagePro())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imageCart);

        btnValues.setText(cart.getNumberPro() + "");
        int sl = Integer.parseInt(btnValues.getText().toString());
        if(sl >= 10){
            btnPlus.setVisibility(View.INVISIBLE);
            btnMinus.setVisibility(View.VISIBLE);
        }
        else if(sl <= 1){
            btnMinus.setVisibility(View.INVISIBLE);
        }
        else if(sl >= 1){
            btnPlus.setVisibility(View.VISIBLE);
            btnMinus.setVisibility(View.VISIBLE);
        }
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoinhat = Integer.parseInt(btnValues.getText().toString()) + 1;
                int slht      = MainActivity.dataCart.get(i).getNumberPro();
                long priceht  = MainActivity.dataCart.get(i).getPricePro();
                MainActivity.dataCart.get(i).setNumberPro(slmoinhat);
                long pricemoinhat = (priceht * slmoinhat) / slht;
                MainActivity.dataCart.get(i).setPricePro(pricemoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtPriceCart.setText(decimalFormat.format(pricemoinhat) + " Đ");
                CartActivity.EventUltil();
                if(slmoinhat > 9){
                    btnPlus.setVisibility(View.INVISIBLE);
                    btnMinus.setVisibility(View.VISIBLE);
                    btnValues.setText(String.valueOf(slmoinhat));
                }
                else {
                    btnPlus.setVisibility(View.VISIBLE);
                    btnMinus.setVisibility(View.VISIBLE);
                    btnValues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(btnValues.getText().toString()) - 1;
                int slht = MainActivity.dataCart.get(i).getNumberPro();
                long priceht = MainActivity.dataCart.get(i).getPricePro();
                MainActivity.dataCart.get(i).setNumberPro(slmoinhat);
                long pricemoinhat = (priceht * slmoinhat) / slht;
                MainActivity.dataCart.get(i).setPricePro(pricemoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtPriceCart.setText(decimalFormat.format(pricemoinhat) + " Đ");
                CartActivity.EventUltil();
                if (slmoinhat < 2) {
                    btnMinus.setVisibility(View.INVISIBLE);
                    btnPlus.setVisibility(View.VISIBLE);
                    btnValues.setText(String.valueOf(slmoinhat));
                } else {
                    btnPlus.setVisibility(View.VISIBLE);
                    btnMinus.setVisibility(View.VISIBLE);
                    btnValues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
