package com.example.rezwan.letsmakeithappen;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ViewAnimator;

/**
 * Created by rezwan on 4/24/16.
 */
public class WriteReviewFrag extends android.support.v4.app.DialogFragment {


    private View ratingView1, ratingView2;

    public WriteReviewFrag(){

    }


    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.write_review_fragment, container, false);
        intView(rootView);
        return rootView;
    }

    private void intView(View rootView) {
        ratingView1 = rootView.findViewById(R.id.ratingView1);
        ratingView2 = rootView.findViewById(R.id.ratingView2);
        rootView.findViewById(R.id.moreBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingView1.setVisibility(View.GONE);
                ratingView2.setVisibility(View.VISIBLE);
            }
        });
        rootView.findViewById(R.id.lessBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingView2.setVisibility(View.GONE);
                ratingView1.setVisibility(View.VISIBLE);

            }
        });
    }

}
