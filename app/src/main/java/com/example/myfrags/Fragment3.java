package com.example.myfrags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class Fragment3 extends Fragment {

    private TextView text;
    private Button button;
    private FragsData fragsData;
    private Observer<String> numberObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        text = (TextView) view.findViewById(R.id.current);
        button = (Button) view.findViewById(R.id.button_decrease);

        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        numberObserver = new Observer<String>() {
            @Override
            public void onChanged(String string) {

                text.setText(string);
            }
        };

        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Integer i;
                                          if(fragsData.counter.getValue().equals("")){
                                              i=0;
                                          }else i =Integer.parseInt(fragsData.counter.getValue());
                                          --i;
                                          String s=i.toString();
                                          fragsData.counter.setValue(s);
                                      }
                                  }
        );

        return view;
    }
}