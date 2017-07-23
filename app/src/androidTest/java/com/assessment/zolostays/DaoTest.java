package com.assessment.zolostays;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.assessment.zolostays.db.DatabaseManager;
import com.assessment.zolostays.db.User;
import com.assessment.zolostays.utils.Utility;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by DELL on 23-07-2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DaoTest {

    private DatabaseManager manager;
    private User user;

    @Before
    public void init(){
        manager = new DatabaseManager(AppController.get(InstrumentationRegistry.getTargetContext()));
        user = new User();
    }

    @Test
    public void testPreConditions(){
        Assert.assertNotNull(manager);
    }

    @Test
    public void insertUserTest(){
        user.setUser_id(Utility.createID());
        user.setName("Mahalakshmi");
        user.setEmail("maha9533@gmail.com");
        user.setPhone("9394791882");
        user.setPassword("lalitha10");
        Assert.assertEquals("Registered Successfully", manager.saveUser(user));

    }
}
