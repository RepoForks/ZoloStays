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

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, AppConstants.DATABASE_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
