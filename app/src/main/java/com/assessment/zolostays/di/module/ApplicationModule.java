package com.assessment.zolostays.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.assessment.zolostays.AppController;
import com.assessment.zolostays.db.DatabaseManager;
import com.assessment.zolostays.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DELL on 23-07-2017.
 */
@Module
public class ApplicationModule {


    private final Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context getContext(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }

    @Provides
    DatabaseManager getDatabaseManager(){
        return new DatabaseManager(AppController.get(application));
    }

}
