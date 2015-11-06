package cse5321.roommateapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ryan on 11/2/15.
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

    public void addUser(User user) {
        mUserList.add(user);
    }

    public void removeUser (User user) {
        mUserList.remove(user);
    }

    public User getUser(UUID id) {
        for (User user : mUserList) {
            if (user.getID().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public int size (){
        return mUserList.size();
    }
}
