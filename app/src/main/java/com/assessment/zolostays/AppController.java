package com.assessment.zolostays;

import android.app.Application;
import android.content.Context;

import com.assessment.zolostays.db.DaoMaster;
import com.assessment.zolostays.db.DaoSession;
import com.assessment.zolostays.di.component.ApplicationComponent;
import com.assessment.zolostays.di.component.DaggerApplicationComponent;
import com.assessment.zolostays.di.module.ApplicationModule;
import com.assessment.zolostays.utils.AppConstants;

import org.greenrobot.greendao.database.Database;

/**
 * Created by DELL on 21-07-2017.
 */

public class AppController extends Application {

    public DaoSession daoSession;

    public static AppController get(Context context){
        return (AppController) context.getApplicationContext();
    }

    public ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, AppConstants.DATABASE_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
