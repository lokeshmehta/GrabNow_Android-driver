package com.eysisgmbh.grabnowdeleviry.utils;

import android.text.Editable;
import android.text.TextWatcher ;

import com.eysisgmbh.grabnowdeleviry.R;
import com.google.android.material.textfield.TextInputLayout;

public class UiUtils {

    //public static String namePattern="^[\\p{L}\\s.’\\-,]+$";
    public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    public static void setTextWatcher(TextInputLayout txt, int minLength, String pattern) {
        txt.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < minLength || s.length() == 0) {
                    txt.setBoxStrokeColorStateList(txt.getContext().getResources().getColorStateList(R.color.box_error));
                    txt.setDefaultHintTextColor(txt.getContext().getResources().getColorStateList(R.color.box_error));

                } else {
                    if (pattern != null && !s.toString().matches(pattern)) {
                        txt.setBoxStrokeColorStateList(txt.getContext().getResources().getColorStateList(R.color.box_error));
                        txt.setDefaultHintTextColor(txt.getContext().getResources().getColorStateList(R.color.box_error));

                    } else {
                        txt.setBoxStrokeColorStateList(txt.getContext().getResources().getColorStateList(R.color.box_no_error));
                        txt.setDefaultHintTextColor(txt.getContext().getResources().getColorStateList(R.color.colorPrimary));
                    }
                }
            }
        });

    }
}
