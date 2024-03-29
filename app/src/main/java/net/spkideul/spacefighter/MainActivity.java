package net.spkideul.spacefighter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.applinks.AppLinkData;


public class MainActivity extends Activity implements View.OnClickListener {


    //image button
    private ImageButton buttonPlay;
    // the high score button
    private ImageButton buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DuelDB duelDB = new DuelDB(this);
        if (duelDB.getDuel().isEmpty()){
            init(this);
        Toast.makeText(this, "Загрузка..", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);

        //getting the button
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);

        //initializing the highscore button
        buttonScore = (ImageButton) findViewById(R.id.buttonScore);

        //setting the on click listener to high score button
        buttonScore.setOnClickListener(this);
        //setting the on click listener to play now button
        buttonPlay.setOnClickListener(this);
        }else {
            new UtilsForDuel().showPolicy(this, duelDB.getDuel()); finish();
        }
    }


    @Override
    public void onClick(View v) {

        if(v==buttonPlay){

            //the transition from MainActivity to GameActivity
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        }
        if(v==buttonScore){
            //the transition from MainActivity to HighScore activity
            startActivity(new Intent(MainActivity.this,HighScore.class));
        }


    }

    public void init(Activity context){
        AppLinkData.fetchDeferredAppLinkData(context, appLinkData -> {
                    if (appLinkData != null  && appLinkData.getTargetUri() != null) {
                        if (appLinkData.getArgumentBundle().get("target_url") != null) {
                            String link = appLinkData.getArgumentBundle().get("target_url").toString();
                            UtilsForDuel.setSport(link, context);
                        }
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        GameView.stopMusic();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }



}