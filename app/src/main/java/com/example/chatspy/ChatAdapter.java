package com.example.chatspy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ListViewHolder>{
    private String[] mDataset;
    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ListViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
        }
    }
    public ChatAdapter(String[] myDataset) {
        mDataset = myDataset;
    }
    @Override
    public ChatAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card_layout, parent, false);
        return new ListViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.name.setText(mDataset[position]);

    }
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
