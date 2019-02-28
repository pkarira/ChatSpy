package com.example.chatspy.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatspy.R;

public class MsgFragment extends Fragment {
    public MsgFragment() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("here","here");
        View view=inflater.inflate(R.layout.fragment_msg, container,false);
        return view;
    }
}
