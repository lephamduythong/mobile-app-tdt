package com.lanbao.christ.shoponline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanbao.christ.shoponline.R;
import com.lanbao.christ.shoponline.model.Bill;
import java.util.ArrayList;

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
        public TextView txtNamePro, txtDate, txtStatus;
    }

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
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Bill bill = (Bill) getItem(i);
        viewHolder.txtNamePro.setText(Integer.toString(bill.getIdpro()));
        viewHolder.txtDate.setText(bill.getDate());
        viewHolder.txtStatus.setText(Integer.toString(bill.getStatus()));

        return view;
    }
}
