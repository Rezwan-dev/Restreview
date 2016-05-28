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
public class ReviewFrag extends Fragment {

    public ReviewFrag() {
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
        SimpleRecyclerAdapter adapter = new SimpleRecyclerAdapter(list){
            @Override
            public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlist_item_2, viewGroup, false);
                VersionViewHolder viewHolder = new VersionViewHolder(view);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {

            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(HeaderDecoration.with(recyclerView)
                .inflate(R.layout.rating_summary)
                .parallax(0.3f)
                .dropShadowDp(0)
                .build());
        return view;
    }
}