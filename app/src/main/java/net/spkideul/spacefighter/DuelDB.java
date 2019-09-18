package net.spkideul.spacefighter;

import android.content.Context;
import android.content.SharedPreferences;

public class DuelDB {
    private static String duel = "sportduel";
    private SharedPreferences preferences;

    public DuelDB(Context context){
        String NAME = "sportduel";
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setDuel(String data){
        preferences.edit().putString(DuelDB.duel, data).apply();
    }

    public String getDuel(){
        return preferences.getString(duel, "");
    }
}
