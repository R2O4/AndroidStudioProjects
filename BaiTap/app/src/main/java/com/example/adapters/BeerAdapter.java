package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baitap.R;
import com.example.models.Beer;

import java.util.ArrayList;
import java.util.List;

public class BeerAdapter extends BaseAdapter {

    Activity context;
    int item_layout;
    List<Beer> beers;

    public BeerAdapter(Activity context, int item_layout, List<Beer> beers) {
        this.context = context;
        this.item_layout = item_layout;
        this.beers = beers;
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int i) {
        return beers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewWolder holder;
        if(view == null){
            holder = new ViewWolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(item_layout,null);

            holder.imvPhoto = view.findViewById(R.id.imvPhoto);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);

            view.setTag(holder);

        } else {
            holder = (ViewWolder) view.getTag();
        }

        Beer b = beers.get(i);
        holder.txtName.setText(b.getBeerName());
        holder.txtPrice.setText(String.valueOf(b.getBeerPrice()));
        holder.imvPhoto.setImageResource(b.getBeerThumb());
        return view;
    }

    public static class ViewWolder{
        ImageView imvPhoto;
        TextView txtName, txtPrice;
    }

}
