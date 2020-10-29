package com.example.myfrags;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class Fragment4 extends Fragment {

    //1.
    private FragsData fragsData;
    private Observer<String> numberObserver;

    //2.
    private EditText edit;
    private TextWatcher textWatcher;
    private boolean turnOffWatcher;
    private boolean act;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_4, container, false);

        //1.
        edit = view.findViewById(R.id.editTextNumber);


        //2.
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        //3.
        numberObserver = new Observer<String>() {
            @Override
            public void onChanged(String newString) {
               if(!act){
                   turnOffWatcher = true;
                   edit.setText(newString);
               }

            }
        };

        //4.
        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);


        //5.
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              if(s.toString().length()==0){
                  turnOffWatcher=true;
              }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!turnOffWatcher){
                    act=true;
                    Integer i;
                    String str;
                    try{
                        i = Integer.parseInt(s.toString());
                        str=s.toString();
                    } catch (NumberFormatException e){
                        str="";
                    }
                    fragsData.counter.setValue(str);
                   act=false;
                } else {
                    turnOffWatcher = !turnOffWatcher;
                }
            }

        };

        //6.
        edit.addTextChangedListener(textWatcher);

        return view;
    }
}