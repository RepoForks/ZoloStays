package com.assessment.zolostays.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.assessment.zolostays.R;
import com.assessment.zolostays.databinding.ActivityRegisterBinding;
import com.assessment.zolostays.db.DatabaseManager;
import com.assessment.zolostays.db.model.User;
import com.assessment.zolostays.utils.Utility;
import com.assessment.zolostays.viewmodel.RegistrationViewModel;

import br.com.ilhasoft.support.validation.Validator;

public class RegisterActivity extends AppCompatActivity{

    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        RegistrationViewModel model = new RegistrationViewModel(this);
        final Validator validator = new Validator(binding);
        binding.setRegister(model);
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();
        databaseManager = new DatabaseManager();
        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validator.validate()){
                    User user = new User();
                    user.setUser_id(Utility.createID());
                    user.setName(binding.registerName.getText().toString().trim());
                    user.setEmail(binding.registerEmail.getText().toString().trim());
                    user.setPhone(binding.registerPhone.getText().toString().trim());
                    user.setPassword(binding.registerPassword.getText().toString().trim());
                    String result = databaseManager.saveUser(user);
                    Utility.showSnackBar(RegisterActivity.this, view.getRootView(), result);
                    if (result.contains("Success")){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(RegisterActivity.this,  LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }, 1700);
                    }
                }
                else{
                    Utility.showSnackBar(RegisterActivity.this, view.getRootView(), "Error in filling form");
                }
            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
