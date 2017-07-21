package com.assessment.zolostays.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.assessment.zolostays.BR;
import com.assessment.zolostays.R;
import com.assessment.zolostays.utils.SimpleTextWatcher;
import com.assessment.zolostays.utils.Utility;

/**
 * Created by DELL on 21-07-2017.
 */

public class RegistrationViewModel extends BaseObservable{

    public Context context;
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    public RegistrationViewModel(Context context) {
        this.context = context;
    }

    public View.OnClickListener onLoginButtonClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
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

    public TextWatcher getNameWatcher(){
        return new SimpleTextWatcher(){
            @Override
            public void afterTextChanged(Editable editable) {
                if (!name.get().equals(editable.toString())){
                    name.set(editable.toString());
                }
            }
        };
    }

    public TextWatcher getEmailWatcher(){
        return new SimpleTextWatcher(){
            @Override
            public void afterTextChanged(Editable editable) {
                if (!email.get().equals(editable.toString())){
                    email.set(editable.toString());
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
