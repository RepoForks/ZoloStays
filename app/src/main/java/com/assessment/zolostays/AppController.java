package com.assessment.zolostays;

import android.app.Application;

import com.assessment.zolostays.db.DaoMaster;
import com.assessment.zolostays.db.DaoSession;
import com.assessment.zolostays.utils.AppConstants;

import org.greenrobot.greendao.database.Database;

/**
 * Created by DELL on 21-07-2017.
 */

public class AppController extends Application {

    private DaoSession daoSession;
    public static AppController instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, AppConstants.DATABASE_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static synchronized AppController getInstance() {
        return instance;
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
