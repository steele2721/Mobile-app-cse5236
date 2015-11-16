package cse5321.roommateapp;

import android.content.Context;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * List Class for Users. Holds all of the users.
 */
public class UserList extends ArrayList<User>{
    public static UserList sUserList;
    public List<User> mUserList;

    public static UserList get() {
        if (sUserList == null) {
            sUserList = new UserList();
        }
        return sUserList;
    }

    public UserList() {
        mUserList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return mUserList;
    }

    public void addUser(User user) {
        mUserList.add(user);
    }

    public void removeUser (User user) {
        mUserList.remove(user);
    }

    public User getUser(String userName) {
        for (User user : mUserList) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public void recreate (List<ParseUser> objects){
        mUserList = new ArrayList<>();
        for (ParseUser object : objects) {
            User user = new User(object);
            mUserList.add(user);
        }
    }

    public int size (){
        return mUserList.size();
    }
}
