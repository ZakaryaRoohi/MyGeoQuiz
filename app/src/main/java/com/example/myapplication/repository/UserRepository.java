package com.example.myapplication.repository;

import com.example.myapplication.model.Setting;

public class UserRepository {
    private static UserRepository sUserRepository;

    public static UserRepository getInstance() {
        if (sUserRepository == null) {
            sUserRepository = new UserRepository();
        }
        return sUserRepository;
    }

    private Setting mSetting;

    public void SetSetting(Setting setting) {
        mSetting = setting;
    }

    public Setting getSetting() {
        return mSetting;
    }

    private UserRepository() {

    }
}
