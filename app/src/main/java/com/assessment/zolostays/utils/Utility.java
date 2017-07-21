package com.assessment.zolostays.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.assessment.zolostays.R;

/**
 * Created by DELL on 21-07-2017.
 */

public class Utility {

    public static void showSnackBar(Context context, View view, String text) {
        Snackbar sb = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        View v = sb.getView();
        v.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.RED);
        sb.show();
    }
}
