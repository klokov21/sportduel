package net.simplifiedcoding.spacefighter;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsUtils {
    private static String data = "data";
    private SharedPreferences preferences;

    public PrefsUtils(Context context){
        String NAME = "data";
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setData(String data){
        preferences.edit().putString(PrefsUtils.data, data).apply();
    }

    public String getData(){
        return preferences.getString(data, "");
    }
}
