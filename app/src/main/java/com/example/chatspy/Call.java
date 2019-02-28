package com.example.chatspy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Call extends Fragment {
    public Call()
    {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("lifecycle frag","onCreateView invoked");
        View view=inflater.inflate(R.layout.fragment_call, container,false);
        return view;
    }
}
