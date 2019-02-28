package com.example.chatspy.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatspy.R;

public class CallFragment extends Fragment {
    public CallFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("lifecycle frag", "onCreateView invoked");
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        return view;
    }
}
