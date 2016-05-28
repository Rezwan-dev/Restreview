package com.example.rezwan.letsmakeithappen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rezwan on 4/24/16.
 */
public class FilterFragment extends android.support.v4.app.DialogFragment {

    private Nearby nearBy;
    private TextView sort_best,sort_distance,
            sort_name,sort_rating,distanceTv,
            priceTv;
    int distanceMulitplier = 1;
    String distanceUnit =  " km.";
    private SeekBar distanceSeekBar;
    private TextView select_cuisine;
    private TextView select_platter;

    public FilterFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nearBy = (Nearby)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.filter_fragment, container, false);
        intView(rootView);
        return rootView;
    }

    private void intView(View rootView) {
        ((BlurView)rootView.findViewById(R.id.blurView)).setBlurredView(nearBy.getRootView());

        sort_best = (TextView) rootView.findViewById(R.id.sort_best);
        sort_distance = (TextView) rootView.findViewById(R.id.sort_distance);
        sort_name = (TextView) rootView.findViewById(R.id.sort_name);
        sort_rating = (TextView) rootView.findViewById(R.id.sort_rating);
        distanceTv = (TextView) rootView.findViewById(R.id.distanceTv);
        priceTv = (TextView) rootView.findViewById(R.id.priceTv);
        distanceSeekBar = (SeekBar)rootView.findViewById(R.id.seekDistanceBar);
        select_cuisine =  (TextView)rootView.findViewById(R.id.select_cuisine);
        select_platter =  (TextView)rootView.findViewById(R.id.select_platter);


        sort_best.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));

        sort_best.setOnClickListener(sort_listener);
        sort_distance.setOnClickListener(sort_listener);
        sort_name.setOnClickListener(sort_listener);
        sort_rating.setOnClickListener(sort_listener);
        ((SwitchCompat)(rootView.findViewById(R.id.toggleBtn))).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                     distanceMulitplier = 1;
                     distanceUnit =  " km.";
                }else{
                    distanceMulitplier = 1000;
                    distanceUnit =  " m.";
                }
                double val  = (((distanceSeekBar.getProgress()/100.0)*4.5*distanceMulitplier)+(0.5 * distanceMulitplier));
                distanceTv.setText(String.format(java.util.Locale.US,"%.1f", val)+distanceUnit);
            }
        });

        distanceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double val  = (((progress/100.0)*4.5*distanceMulitplier)+(0.5 * distanceMulitplier));
                distanceTv.setText(String.format(java.util.Locale.US,"%.1f", val)+distanceUnit);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ((SeekBar)rootView.findViewById(R.id.seekPriceBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priceTv.setText(((int)((progress/100.0)*2800)+200)+" Tk/person.");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        select_cuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultipleCuisineChoiceDialog dialogFragment =  new MultipleCuisineChoiceDialog();
                dialogFragment.show(getActivity().getSupportFragmentManager(),"Multi");
                dialogFragment.setCallBackApply(new MultipleCuisineChoiceDialog.CallBackApply() {
                    @Override
                    public void onApply(ArrayList<Integer> arrayList) {
                        String[] values = getResources().getStringArray(R.array.cuisines);
                        String result = "";
                        for( int i = 0; i < arrayList.size() && i < 5; i++){
                            result += values[arrayList.get(i)]+", ";
                        }
                        select_cuisine.setText(result.substring(0,result.length()-2));
                    }
                });
            }
        });
        select_platter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiplePaltterChoiceDialog dialogFragment =  new MultiplePaltterChoiceDialog();
                dialogFragment.show(getActivity().getSupportFragmentManager(),"Multi");
                dialogFragment.setCallBackApply(new MultiplePaltterChoiceDialog.CallBackApply() {
                    @Override
                    public void onApply(ArrayList<Integer> arrayList) {
                        String[] values = getResources().getStringArray(R.array.cuisines);
                        String result = "";
                        for( int i = 0; i < arrayList.size() && i < 5; i++){
                            result += values[arrayList.get(i)]+", ";
                        }
                        select_platter.setText(result.substring(0,result.length()-2));
                    }
                });
            }
        });
    }

    View.OnClickListener sort_listener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sort_best.setTextColor(Color.WHITE);
            sort_distance.setTextColor(Color.WHITE);
            sort_name.setTextColor(Color.WHITE);
            sort_rating.setTextColor(Color.WHITE);
            ((TextView)v).setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
        }
    };

/*    public class DialogoSeleccion extends android.support.v4.app.DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final String[] items = {"Español", "Inglés", "Francés"};

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());

            builder.setTitle("Selección")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            Log.i("Dialogos", "Opción elegida: " + items[item]);
                        }
                    });

            return builder.create();
        }
    }*/

}
