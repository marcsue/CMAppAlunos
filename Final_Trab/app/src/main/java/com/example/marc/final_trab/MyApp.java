package com.example.marc.final_trab;

import android.app.Application;

/**
 * Created by user on 26/07/2017.
 */

public class MyApp extends Application {

    private String mGlobalVarValue="";

    public String getGlobalVarValue() {
        return mGlobalVarValue;
    }

    public void setGlobalVarValue(String str) {
        mGlobalVarValue += str+"\n";
    }
    public void setGlobalClear() {
        mGlobalVarValue ="";
    }

}
