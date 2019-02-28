package com.example.chatspy.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.chatspy.Fragments.ChatFragment;
import com.example.chatspy.Parsing_Classes.Message;
import com.example.chatspy.Adapters.MessageListAdapter;
import com.example.chatspy.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        String s = getIntent().getStringExtra("Contact Address");
        TextView title = (TextView) findViewById(R.id.chat_title);
        title.setText(s);
        ArrayList<Message> mMessageList = ChatFragment.contactHashMap.get(s).getMessages();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_chat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MessageListAdapter(this, mMessageList);
        recyclerView.setAdapter(mAdapter);
    }
}
