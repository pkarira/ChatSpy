package com.example.chatspy;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Map<String, Contact> contactHashMap;
    private ArrayList<String> contactAddress;
    private String address;
    private String date;

    public ChatFragment() {
        contactHashMap = new HashMap<>();
        contactAddress = new ArrayList<>();
    }

    private void createContacts() {
        Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) {
            int row = 0;
            do {
                Message msg = new Message();
                msg.setMsg(cursor.getString(12));
                date = cursor.getString((4));
                SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String java_date = jdf.format(new Date(Long.parseLong(date)));
                msg.setDate(java_date.substring(0,10));
                msg.setType(Integer.parseInt(cursor.getString(9)));
                address = cursor.getString(2);
                if (address.length() > 10)
                    address = address.substring(3);
                msg.setSender(address);
                if (contactHashMap.get(address) == null) {
                    Contact contact = new Contact();
                    contact.setAddress(address);
                    contactAddress.add(address);
                    contactHashMap.put(address, contact);
                }
                contactHashMap.get(address).addMessage(msg);
                Log.d("inbox", address + " " + cursor.getString(12));
                cursor.moveToNext();
                row++;
            } while (cursor.moveToNext() && row < 100);
        }
        Cursor cursor_sent = getActivity().getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
        if (cursor_sent.moveToFirst()) {
            int row = 0;
            do {
                Message msg = new Message();
                msg.setMsg(cursor_sent.getString(12));
                date = cursor.getString((4));
                SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String java_date = jdf.format(new Date(Long.parseLong(date)));
                msg.setDate(java_date.substring(0,10));
                msg.setType(Integer.parseInt(cursor_sent.getString(9)));
                address = cursor_sent.getString(2);
                if (address.length() > 10)
                    address = address.substring(3);
                msg.setSender(address);
                if (contactHashMap.get(address) == null) {
                    Contact contact = new Contact();
                    contact.setAddress(address);
                    contactAddress.add(address);
                    contactHashMap.put(address, contact);
                }
                contactHashMap.get(address).addMessage(msg);
                Log.d("sent", address + " " + cursor_sent.getString(12));
                cursor_sent.moveToNext();
                row++;
            } while (cursor_sent.moveToNext() && row < 100);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        createContacts();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ChatAdapter(contactAddress, new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                Intent intent = new Intent(getActivity(), ChatActivity.class).putExtra("Contact Address", item);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
