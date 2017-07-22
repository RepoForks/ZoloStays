package com.assessment.zolostays.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.assessment.zolostays.R;
import com.assessment.zolostays.databinding.ActivityLoginBinding;
import com.assessment.zolostays.db.DatabaseManager;
import com.assessment.zolostays.utils.PrefUtils;
import com.assessment.zolostays.utils.Utility;
import com.assessment.zolostays.viewmodel.LoginViewModel;

import br.com.ilhasoft.support.validation.Validator;

public class LoginActivity extends AppCompatActivity {
    public PrefUtils prefUtils;
    public DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefUtils = new PrefUtils(this);
        if (prefUtils.isLogin()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(intent);
        }
        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel model = new LoginViewModel(this);
        final Validator validator = new Validator(binding);
        binding.setUvm(model);
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();
        databaseManager = new DatabaseManager();
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validator.validate()){
                    if (databaseManager.loginCheck(binding.loginPhone.getText().toString().trim(),
                                        binding.loginPassword.getText().toString().trim())){
                        prefUtils.setIslogin(true);
                        prefUtils.save(databaseManager.getUserByPhone(binding.loginPhone.getText().toString().trim()));
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(intent);
                    }
                    else{
                        Utility.showSnackBar(LoginActivity.this, view.getRootView(), "Invalid login details");
                    }
                }
                else{
                    Utility.showSnackBar(LoginActivity.this, view.getRootView(), "Error in filling form");
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
