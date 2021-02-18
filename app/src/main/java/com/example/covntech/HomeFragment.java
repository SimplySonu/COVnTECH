package com.example.covntech;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    public HomeFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView World = (TextView) rootView.findViewById(R.id.world_text);
        World.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent world = new Intent( getActivity(), World_Activity.class);
                startActivity(world);
            }
        });

        TextView India = (TextView) rootView.findViewById(R.id.india_text);
        India.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent world = new Intent( getActivity(), India_Activity.class);
                startActivity(world);
            }
        });


        return rootView; }
}