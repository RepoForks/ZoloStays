package com.assessment.zolostays.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.assessment.zolostays.AppController;
import com.assessment.zolostays.db.DatabaseManager;
import com.assessment.zolostays.di.ApplicationContext;
import com.assessment.zolostays.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DELL on 23-07-2017.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AppController controller);

    @ApplicationContext
    Context getContext();

    DatabaseManager getDatabaseManager();

}
