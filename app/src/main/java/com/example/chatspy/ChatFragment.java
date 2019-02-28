package com.example.chatspy;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Map<String,Contact> contactHashMapReceived;
    private Map<String,Contact> contactHashMapSent;
    private ArrayList<String> contactAddress;
    private String address;
    public ChatFragment() {
        contactHashMapReceived=new HashMap<>();
        contactHashMapSent=new HashMap<>();
        contactAddress=new ArrayList<>();
    }
    private void createContactsReceived()
    {
        Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) {
            int row=0;
           do {
                Message msg=new Message();
                msg.setMsg(cursor.getString(12));
                msg.setDate(cursor.getString(4));
                address=cursor.getString(2);
                if(contactHashMapReceived.get(address)==null)
                {
                    Contact contact=new Contact();
                    contact.setAddress(address);
                    contactAddress.add(address);
                    contactHashMapReceived.put(address,contact);
                }
                contactHashMapReceived.get(address).addReceived(msg);
                cursor.moveToNext();
                row++;
            }while(cursor.moveToNext() && row<100);
        }else {

        }
    }
    private void createContactsSent()
    {
        Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
        if (cursor.moveToFirst()) {
            int row=0;
            do{
                Message msg=new Message();
                msg.setMsg(cursor.getString(12));
                msg.setDate(cursor.getString(4));
                address=cursor.getString(2);
                if(contactHashMapSent.get(address)==null)
                {
                    Contact contact=new Contact();
                    contact.setAddress(address);
                    contactAddress.add(address);
                    contactHashMapSent.put(address,contact);
                }
                contactHashMapSent.get(address).addSent(msg);
                cursor.moveToNext();
                row++;
            }while(cursor.moveToNext() && row<100);
        }else {

        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chat, container,false);
        createContactsReceived();
        createContactsSent();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ChatAdapter(contactAddress);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
