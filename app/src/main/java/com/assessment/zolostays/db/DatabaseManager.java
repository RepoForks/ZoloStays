package com.assessment.zolostays.db;

import com.assessment.zolostays.AppController;
import com.assessment.zolostays.db.model.User;

import java.util.List;

/**
 * Created by DELL on 21-07-2017.
 */

public class DatabaseManager {

    public UserDao userDao;

    public DatabaseManager(){
        userDao = AppController.getInstance().getDaoSession().getUserDao();
    }

    public String saveUser(User user){
        if (!checkUser(user)){
            try {
                userDao.insert(user);
                return "Registered Successfully";
            }
            catch (Exception e){
                e.printStackTrace();
                return "Registration failed..!!Please try again.";
            }
        }
        else return "User already exists..!!";
    }

    public boolean checkUser(User user){
        List<User> users = getAllUsers();
        if (users.size() > 0){
            for (User u : users){
                if (u.getEmail().equals(user.getEmail()) || u.getPhone().equals(user.getPhone())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkEmail(String email){
        List<User> users = getAllUsers();
        if (users.size() > 0){
            for (User u : users){
                if (u.getEmail().equals(email)){
                    return true;
                }
            }
        }
        return false;
    }

    public List<User> getAllUsers(){
        return userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build().list();
    }

    public boolean loginCheck(String phone, String password){
        List<User> users = getAllUsers();
        if (users.size() > 0){
            for (User u : users){
                if (u.getPhone().equals(phone)){
                    if (u.getPassword().equals(password))
                        return true;
                }
            }
        }
        return false;
    }

    public String updateUser(User user){
        List<User> users = getAllUsers();
        if (users.size() > 0){
            for (User u : users){
                if (u.getEmail().equals(user.getEmail())){
                    try {
                        userDao.insertOrReplace(user);
                        return "Password has been sent to your Email";
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        return "Error in resetting";
                    }
                }
            }
        }
        return "User already exists..!!";
    }

    public boolean updateUser(User user, boolean mode){
        List<User> users = getAllUsers();
        if (users.size() > 0){
            for (User u : users){
                if (u.getUser_id()== user.getUser_id()){
                    try {
                        userDao.insertOrReplace(user);
                        return true;
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public User getUser(String email){
        List<User> users = getAllUsers();
        if (users.size() > 0){
            for (User u : users){
                if (u.getEmail().equals(email)){
                    return u;
                }
            }
        }
        return null;
    }

    public User getUserByPhone(String phone){
        List<User> users = getAllUsers();
        if (users.size() > 0){
            for (User u : users){
                if (u.getPhone().equals(phone)){
                    return u;
                }
            }
        }
        return null;
    }


}
