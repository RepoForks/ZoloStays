package com.assessment.zolostays.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.assessment.zolostays.BR;
import com.assessment.zolostays.db.User;
import com.assessment.zolostays.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by DELL on 22-07-2017.
 */
@Singleton
public class MainViewModel extends BaseObservable {

    public Context context;

    public ObservableField<User> user = new ObservableField<>();

    @Inject
    public MainViewModel(@ApplicationContext Context context, User user) {
        this.context = context;
        this.user = new ObservableField<>(user);
    }

    public Long getId() {
        return user.get().getUser_id();
    }

    public void setId(long id) {
        user.get().setUser_id(id);
    }

    @Bindable
    public String getPhone() {
        return user.get().getPhone();
    }
    public void setPhone(String phone) {
        user.get().setPhone(phone);
        notifyPropertyChanged(BR.phone);
    }
    @Bindable
    public String getName() {
        return user.get().getName();
    }

    public void setName(String name) {
        user.get().setName(name);
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getEmail() {
        return user.get().getEmail();
    }

    public void setEmail(String email) {
        user.get().setEmail(email);
        notifyPropertyChanged(BR.email);
    }

    public String getPassword() {
        return user.get().getPassword();
    }

    public void setPassword(String password) {
        user.get().setPassword(password);
    }
}
