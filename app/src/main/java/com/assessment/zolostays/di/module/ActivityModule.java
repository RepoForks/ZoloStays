package com.assessment.zolostays.di.module;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.assessment.zolostays.AppController;
import com.assessment.zolostays.db.User;
import com.assessment.zolostays.utils.PrefUtils;
import com.assessment.zolostays.viewmodel.LoginViewModel;
import com.assessment.zolostays.viewmodel.MainViewModel;
import com.assessment.zolostays.viewmodel.RegistrationViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DELL on 23-07-2017.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }

    @Provides
    Context provideContext(){
        return mActivity;
    }

    SharedPreferences provideSharedPreferences(){
        return mActivity.getSharedPreferences("zolostays", Context.MODE_PRIVATE);
    }

    @Provides
    PrefUtils getPrefUtils(){
        return new PrefUtils(mActivity, provideSharedPreferences());
    }

    @Provides
    AppController getAppController(){
        return AppController.get(mActivity);
    }

    @Provides
    MainViewModel getMainViewModel(){
        return new MainViewModel(mActivity, getPrefUtils().getCurrentUser());
    }

    @Provides
    RegistrationViewModel getRegistrationViewModel(){
        return new RegistrationViewModel(mActivity);
    }

    @Provides
    LoginViewModel getLoginViewModel(){
        return new LoginViewModel(mActivity);
    }
}
