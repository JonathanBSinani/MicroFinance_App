package com.a11group.microfinanceapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.a11group.microfinanceapp.R;

/**
 * Created by Jonathan on 26/06/2017.
 */

public class LineHolder extends RecyclerView.ViewHolder{

    public TextView categoryTV;
    public TextView txtDateCreate;
    public TextView txtmontly;
    public ImageButton deleteButton;

    public LineHolder(View itemView) {
        super(itemView);
        //categoryTV = (TextView) itemView.findViewById(R.id.txt_category);
        txtDateCreate = (TextView) itemView.findViewById(R.id.txtDateCreate);
        txtmontly = (TextView) itemView.findViewById(R.id.txtmontly);

        deleteButton = (ImageButton) itemView.findViewById(R.id.imbDelete);
    }
}
