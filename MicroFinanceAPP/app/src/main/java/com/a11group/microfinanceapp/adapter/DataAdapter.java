package com.a11group.microfinanceapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.a11group.microfinanceapp.R;
import com.a11group.microfinanceapp.models.Simulator;

import java.util.List;
import java.util.Locale;

/**
 * Created by Jonathan on 25/06/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<LineHolder>{

        private Context context;
        private List<Simulator> dataList;

        public DataAdapter(Context context, List<Simulator> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        @Override
        public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_home_screen,parent,false);
            return new LineHolder(view);
        }

        @Override
        public void onBindViewHolder(LineHolder holder, int position) {
            holder.txtDateCreate.setText(dataList.get(position).getCreated_at());
            holder.txtmontly.setText(dataList.get(position).getMonthlyContribution());

        }

        @Override
        public int getItemCount() {
            return dataList != null ? dataList.size() : 0;
        }
}
