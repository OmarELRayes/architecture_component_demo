package com.example.omarelrayes.dos.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omarelrayes.dos.R;
import com.example.omarelrayes.dos.model.Monument;
import com.example.omarelrayes.dos.util.BitmapConverter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Omar ELRayes on 3/21/2018.
 */

public class MonumentsAdapter extends RecyclerView.Adapter<MonumentsAdapter.ViewHolder> {

    List<Monument> monuments;
    Monument monument;
    Context context;

    public MonumentsAdapter(List<Monument> monuments, Context context) {
        this.monuments = monuments;
        this.context = context;
    }

    public void addMonuments(List<Monument> monuments) {
        this.monuments = monuments;
        notifyDataSetChanged();
    }

    @Override
    public MonumentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MonumentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MonumentsAdapter.ViewHolder holder, int position) {
        monument = monuments.get(position);
        holder.title.setText(monument.getTitle());
        holder.img.setImageBitmap(BitmapConverter.byteArrayToBitmap(monument.getImage()));
    }

    @Override
    public int getItemCount() {
        return monuments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recycler_title)
        public TextView title;
        @BindView(R.id.recycler_img)
        public CircleImageView img;
        @BindView(R.id.container)
        CardView container;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Monument item = monuments.get(
                    this.getAdapterPosition()
            );
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("itemId", item.getId());
            context.startActivity(intent);
        }

    }
}
