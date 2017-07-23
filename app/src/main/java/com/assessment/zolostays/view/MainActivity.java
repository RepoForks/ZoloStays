package com.assessment.zolostays.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.assessment.zolostays.AppController;
import com.assessment.zolostays.R;
import com.assessment.zolostays.databinding.ActivityMainBinding;
import com.assessment.zolostays.db.DatabaseManager;
import com.assessment.zolostays.db.User;
import com.assessment.zolostays.di.component.ActivityComponent;
import com.assessment.zolostays.di.component.ApplicationComponent;
import com.assessment.zolostays.di.component.DaggerActivityComponent;
import com.assessment.zolostays.di.component.DaggerApplicationComponent;
import com.assessment.zolostays.di.module.ActivityModule;
import com.assessment.zolostays.di.module.ApplicationModule;
import com.assessment.zolostays.utils.PrefUtils;
import com.assessment.zolostays.utils.Utility;
import com.assessment.zolostays.viewmodel.MainViewModel;

import javax.inject.Inject;

import br.com.ilhasoft.support.validation.Validator;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Inject
    PrefUtils utils;
    @Inject
    DatabaseManager manager;
    @Inject
    MainViewModel viewModel;

    User u;

    ActivityComponent activityComponent;

    public ActivityComponent getComponent(){
        if (activityComponent == null){
            activityComponent = DaggerActivityComponent
                    .builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(AppController.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }

    ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent(){
        if (applicationComponent == null){
            applicationComponent = DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(activityComponent.getAppController()))
                    .build();
        }
        return applicationComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityComponent = getComponent();
        applicationComponent = getApplicationComponent();
        utils = activityComponent.getPrefUtils();
        final User user = utils.getCurrentUser();
        manager = applicationComponent.getDatabaseManager();
        viewModel = activityComponent.getMainViewModel();
        final Validator validator = new Validator(binding);
        binding.setModel(viewModel);
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");
        initNavigationView();
        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validator.validate()){
                    u = new User();
                    u.setUser_id(user.getUser_id());
                    u.setName(binding.registerName.getText().toString().trim());
                    u.setEmail(binding.registerEmail.getText().toString().trim());
                    u.setPhone(binding.registerPhone.getText().toString().trim());
                    u.setPassword(user.getPassword());
                    try {
                        manager.updateUser(u, true);
                        utils.updateUser(u);
                        Utility.showSnackBar(MainActivity.this, view.getRootView(), "Successfully updated");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        Utility.showSnackBar(MainActivity.this, view.getRootView(), "Can't update now");
                    }
                }
                else{
                    Utility.showSnackBar(MainActivity.this, view.getRootView(), "Error in filling form");
                }
            }
        });
    }

    private void initNavigationView(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();
        binding.navgationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.logout){
                    binding.drawer.closeDrawers();
                    logout();
                    return true;
                }
                return false;
            }
        });
    }

    private void logout(){
        utils.setIslogin(false);
        utils.remove();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
