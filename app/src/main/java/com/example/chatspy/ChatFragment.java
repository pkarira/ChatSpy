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
    public static Map<String,Contact> contactHashMap;
    private ArrayList<String> contactAddress;
    private String address;
    public ChatFragment() {
        contactHashMap=new HashMap<>();
        contactAddress=new ArrayList<>();
    }
    private void createContacts()
    {
        Cursor cursor = getActivity().getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
        if (cursor.moveToFirst()) {
            int row=0;
           do {
                Message msg=new Message();
                msg.setMsg(cursor.getString(12));
                msg.setDate(cursor.getString(4));
                msg.setType(Integer.parseInt(cursor.getString(9)));
                address=cursor.getString(2);
                if(contactHashMap.get(address)==null)
                {
                    Contact contact=new Contact();
                    contact.setAddress(address);
                    contactAddress.add(address);
                    contactHashMap.put(address,contact);
                }
                contactHashMap.get(address).addMessage(msg);
                cursor.moveToNext();
                row++;
            }while(cursor.moveToNext() && row<100);
        }else {

        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chat, container,false);
        createContacts();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ChatAdapter(contactAddress,new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                Toast.makeText(getContext(), item, Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
