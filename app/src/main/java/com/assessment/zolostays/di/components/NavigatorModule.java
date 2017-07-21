package com.assessment.zolostays.di.components;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by DELL on 21-07-2017.
 */

public interface NavigatorModule {

    void startActivity(@NonNull Intent intent);
    void startActivity(@NonNull Class<? extends Activity> activityClass);

}
