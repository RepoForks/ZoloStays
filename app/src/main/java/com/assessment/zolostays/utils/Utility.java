package com.assessment.zolostays.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.assessment.zolostays.R;
import com.assessment.zolostays.mail.MailSender;

import java.net.SocketPermission;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by DELL on 21-07-2017.
 */

public class Utility {

    public static void showSnackBar(Context context, View view, String text) {
        Snackbar sb = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        View v = sb.getView();
        v.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        TextView tv = v.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.RED);
        sb.show();
    }

    public static int createID(){
        Date date = new Date();
        return Integer.parseInt(new SimpleDateFormat("MddHHmmss",  Locale.US).format(date));
    }

    public static void showDialogBox(final Context context, String title, String message, final String intent){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.AppTheme_Dialog);
        alertDialogBuilder
                .setMessage(message)
                .setTitle(title)
                .setCancelable(false)
                .setPositiveButton(" Enable ",
                        new DialogInterface.OnClickListener()
                        {

                            public void onClick(DialogInterface dialog, int id)
                            {
                                Intent dialogIntent = new Intent(intent);
                                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(dialogIntent);
                            }
                        });

        alertDialogBuilder.setNegativeButton(" Cancel ", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo  = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
