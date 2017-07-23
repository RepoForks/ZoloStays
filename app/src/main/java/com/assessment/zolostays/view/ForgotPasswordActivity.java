package com.assessment.zolostays.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.assessment.zolostays.AppController;
import com.assessment.zolostays.R;
import com.assessment.zolostays.databinding.ActivityForgotPasswordBinding;
import com.assessment.zolostays.db.DatabaseManager;
import com.assessment.zolostays.db.User;
import com.assessment.zolostays.mail.MailSender;
import com.assessment.zolostays.utils.Utility;
import com.assessment.zolostays.viewmodel.RegistrationViewModel;

import br.com.ilhasoft.support.validation.Validator;

public class ForgotPasswordActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityForgotPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        RegistrationViewModel model = new RegistrationViewModel(this);
        final Validator validator = new Validator(binding);
        binding.setRegister(model);
        databaseManager = new DatabaseManager(AppController.get(this));
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validator.validate()){
                    if (databaseManager.checkEmail(binding.forgotEmail.getText().toString().trim())){
                        if (Utility.isConnected(ForgotPasswordActivity.this)){
                            long password = Utility.createID();
                            final User user = databaseManager.getUser(binding.forgotEmail.getText().toString().trim());
                            if (user != null){
                                user.setPassword(String.valueOf(password));
                                String result = databaseManager.updateUser(user);
                                Log.d(ForgotPasswordActivity.class.getSimpleName(), user.toString());
                                sendEmail(user);
                                Utility.showSnackBar(ForgotPasswordActivity.this, view.getRootView(), result);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(ForgotPasswordActivity.this,  LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }, 1700);
                            }
                            else
                                Utility.showSnackBar(ForgotPasswordActivity.this, view.getRootView(), "Unknown error");
                        }
                        else{
                            Utility.showDialogBox(ForgotPasswordActivity.this,
                                    "No Internet Connection",
                                    "Internet connection not found..!!Please turn on internet to reset password",
                                    Settings.ACTION_WIRELESS_SETTINGS);
                        }
                    }
                    else{
                        Utility.showSnackBar(ForgotPasswordActivity.this, view.getRootView(), "Email not found, please register");
                    }
                }
                else {
                    Utility.showSnackBar(ForgotPasswordActivity.this, view.getRootView(), "Check your Email format");
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

    private void sendEmail(final User user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MailSender sender = new MailSender("prudhvirajkumar520@gmail.com", "prudhvipalace10");
                    sender.sendMail(
                            "ZoloStays Reset Password",
                            getResources().getString(R.string.mailheader) + "\n\n" +
                                    "Your password is " + user.getPassword() + ". Use this password to login" + "\n" +
                                    getResources().getString(R.string.mailFooter),
                            "noreply@zolostays.com",
                            user.getEmail());
                    System.out.print("Successfully sent");
                }
                catch (Exception ee){
                    ee.printStackTrace();
                    System.out.print("Failed to send");
                }
            }
        }).start();
    }
}
