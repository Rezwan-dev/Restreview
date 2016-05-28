package com.example.rezwan.letsmakeithappen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezwan on 4/11/16.
 */
public class DummyFragment extends Fragment {

    public DummyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add("Demo - " + i);
        }
        SimpleRecyclerAdapter adapter = new SimpleRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}