package com.assessment.zolostays.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.assessment.zolostays.db.User;
import com.assessment.zolostays.utils.SimpleTextWatcher;
import com.assessment.zolostays.view.ForgotPasswordActivity;
import com.assessment.zolostays.view.LoginActivity;
import com.assessment.zolostays.view.RegisterActivity;

/**
 * Created by DELL on 20-07-2017.
 */

public class LoginViewModel extends BaseObservable {

    public Context context;
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    public LoginViewModel(Context context) {
        this.context = context;
    }

    public View.OnClickListener onForgotPasswordClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ForgotPasswordActivity.class));
            }
        };
    }

    public View.OnClickListener onCreateAccountClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RegisterActivity.class));
            }
        };
    }

    public TextWatcher getPhoneNumberWatcher(){
        return new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (!phone.get().equals(editable.toString())){
                    phone.set(editable.toString());
                }
            }
        };
    }

    public TextWatcher getPasswordWatcher(){
        return new SimpleTextWatcher(){
            @Override
            public void afterTextChanged(Editable editable) {
                if (!password.get().equals(editable.toString())){
                    password.set(editable.toString());
                }
            }
        };
    }
}
