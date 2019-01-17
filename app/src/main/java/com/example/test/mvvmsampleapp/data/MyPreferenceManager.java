package com.example.test.mvvmsampleapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.test.mvvmsampleapp.R;

public class MyPreferenceManager {

    private String TAG = MyPreferenceManager.class.getSimpleName();
    // Shared Preferences
    private SharedPreferences pref;
    private  static final  String TOKEN ="token";

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    Context _context;

    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        editor = pref.edit();

    }

    public void setToken(String v) {
        editor.putString(TOKEN, v);
        editor.apply();
    }

    public String getToken() {
        if (pref.getString(TOKEN, null) != null) {
            String str;
            str = pref.getString(TOKEN, "");

            return str;
        }
        return "";
    }

    public void  clear()
    {
        editor.clear().commit();
    }




}
