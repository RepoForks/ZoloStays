package com.assessment.zolostays.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.assessment.zolostays.BR;
import com.assessment.zolostays.utils.SimpleTextWatcher;
import com.assessment.zolostays.view.ForgotPasswordActivity;
import com.assessment.zolostays.view.RegisterActivity;

/**
 * Created by DELL on 20-07-2017.
 */

public class LoginViewModel extends BaseObservable {

    public Context context;
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public boolean loginButtonEnabled = false;
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
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!phone.get().equals(charSequence.toString())){
                    phone.set(charSequence.toString());

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkButtonEnabling();
            }
        };
    }

    public TextWatcher getPasswordWatcher(){
        return new SimpleTextWatcher(){
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!password.get().equals(charSequence.toString())){
                    password.set(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkButtonEnabling();
            }
        };
    }

    public void checkButtonEnabling(){
        if (phone.get().length() == 10
                && password.get().length() >= 3 && password.get().length() <= 10){
            setLoginButtonEnabled(true);
        }
        else{
            setLoginButtonEnabled(false);
        }
    }

    @Bindable
    public boolean getLoginButtonEnabled(){
        return loginButtonEnabled;
    }

    public void setLoginButtonEnabled(boolean loginButtonEnabled) {
        this.loginButtonEnabled = loginButtonEnabled;
        notifyPropertyChanged(BR.loginButtonEnabled);
    }
}
