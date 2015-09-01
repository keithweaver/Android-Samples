package com.expeditionlabs.gcmexample.lib.gcm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.expeditionlabs.gcmexample.lib.C;

/**
 * Created by Keith on 2015-08-24.
 */
public class RegisterCommon {

    Context c;
    public RegisterCommon(Context c){
        this.c = c;
    }
    public void storeRegisterId(String reg){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP = c.getSharedPreferences(C.register.ACCOUNT, mode);
        SharedPreferences.Editor editor = mSP.edit();
        editor.putString(C.register.KEY_REG_ID, reg);
        editor.commit();
    }
    public String getStoreRegisterId(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP;
        mSP = c.getSharedPreferences(C.register.ACCOUNT, mode);
        String regId = mSP.getString(C.register.KEY_REG_ID,"");
        return regId;
    }
    public void storeUnregisterId(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP;
        mSP = c.getSharedPreferences(C.register.ACCOUNT, mode);
        SharedPreferences.Editor editor = mSP.edit();
        editor.putString(C.register.KEY_REG_ID, "");
        editor.commit();
    }
}
