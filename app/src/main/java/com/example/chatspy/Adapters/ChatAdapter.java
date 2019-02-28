package com.example.chatspy.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chatspy.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ListViewHolder>{
    private ArrayList<String> mDataset;
    private final OnItemClickListener listener;
    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ListViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
        }
        public void bind(final String item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(String item);
    }
    public ChatAdapter(ArrayList<String> myDataset,OnItemClickListener listener) {
        this.mDataset = myDataset;
        this.listener = listener;
    }
    @Override
    public ChatAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card_layout, parent, false);
        return new ListViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.name.setText(mDataset.get(position));
        holder.bind(mDataset.get(position),listener);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
