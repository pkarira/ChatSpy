package com.example.chatspy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Chat extends Fragment {
    public Chat() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("lifecycle frag","onCreateView invoked");
        View view=inflater.inflate(R.layout.fragment_chat, container,false);

        return view;
    }
    public void me(View v)
    {
        Toast.makeText(getContext(),"asdf",Toast.LENGTH_SHORT).show();
    }
}
