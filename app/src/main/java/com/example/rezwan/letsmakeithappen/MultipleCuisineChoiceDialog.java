package com.example.rezwan.letsmakeithappen;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by rezwan on 5/3/16.
 */
public class MultipleCuisineChoiceDialog extends AppCompatDialogFragment {

    private ArrayList<Integer> selectedIndexes =  new ArrayList<>();
    private CallBackApply callBackApply;
    private String []values;
    private boolean []isValueSelected;
    private AlertDialog dialog;
    private Button neutralButton;

    public interface CallBackApply{
        void onApply(ArrayList<Integer> arrayList);
    }
    public void setCallBackApply(CallBackApply callBackApply ){
        this.callBackApply = callBackApply;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        values  = getResources().getStringArray(R.array.cuisines);
        isValueSelected  = new boolean[values.length];
        isValueSelected[0] = true;
        selectedIndexes.add(0);
        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext(), R.style.CustomMultiSelectDialog);
        builder.setTitle("Cuisines")
                .setMultiChoiceItems(values, isValueSelected,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if(which == 0){
                                    allClear();
                                    return;
                                }
                                if(isChecked){
                                    selectedIndexes.add(which);
                                }else if(selectedIndexes.contains(which)){
                                    selectedIndexes.remove(Integer.valueOf(which));
                                }
                                toggle1stItem(!(selectedIndexes.size() > 0));

                            }
                        })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(callBackApply != null){
                            callBackApply.onApply(selectedIndexes);
                        }
                    }
                })
                .setNeutralButton("Clear all", null);
        return builder.create();
    }

    @Override
    public void onStart()
    {
        super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        dialog = (AlertDialog)getDialog();
        if(dialog != null)
        {
            neutralButton = (Button) dialog.getButton(Dialog.BUTTON_NEUTRAL);
            neutralButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                 allClear();
                }
            });
            neutralButton.setEnabled(false);
        }
    }

    public void allClear(){
        for(int i = 0; i < dialog.getListView().getCount(); i ++){
            isValueSelected[i] = i == 0;
            dialog.getListView().setItemChecked(i , i == 0);
        }
        selectedIndexes.clear();
        selectedIndexes.add(0);
        neutralButton.setEnabled(false);
    }

    public void toggle1stItem(boolean b){
        isValueSelected[0] = b;
        dialog.getListView().setItemChecked(0 ,b);
        if(selectedIndexes.contains(0) && !b){
            selectedIndexes.remove(Integer.valueOf(0));

        }
        neutralButton.setEnabled(!b);


    }

}
