package com.eysisgmbh.grabnowdeleviry.custom;

import android.view.View;

import androidx.fragment.app.Fragment;

public class CustomFragment extends Fragment implements View.OnClickListener {


    public View setClick(View v) {
        v.setOnClickListener(this);
        return v;
    }

    public View setTouchClick(View v) {
        v.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
