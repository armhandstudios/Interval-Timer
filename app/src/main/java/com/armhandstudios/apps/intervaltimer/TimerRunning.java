package com.armhandstudios.apps.intervaltimer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerRunning extends AppCompatActivity
{
    private String mainTitle;
    private String secondaryTitle;
    private int mainTime;
    private int restTime;
    private int reps;
    private final Handler timerHandler = new Handler();
    private MediaPlayer toner;
    boolean activityEnded;
    Button backButton;
    TextView intervalName;
    TextView activityTimer;
    TextView nRepsText;
    TextView nReps;
    Runnable ready;
    Runnable set;
    Runnable go;
    Runnable active;
    Runnable rest;
    Runnable toRun;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_timer_running);

        //get data from bundle
        Intent fromMain = getIntent();
        Bundle bundle = fromMain.getExtras();
        if(bundle != null && !bundle.isEmpty())
        {
            mainTitle = bundle.getString("mainActivityTitle");
            mainTime = bundle.getInt("mainTime");
            secondaryTitle = bundle.getString("secondaryActivityTitle");
            restTime = bundle.getInt("restTime");
            reps = bundle.getInt("nReps");
        }

        backButton = (Button) findViewById(R.id.backButton);
        intervalName = (TextView) findViewById(R.id.intervalName);
        activityTimer = (TextView) findViewById(R.id.activityTimer);
        nRepsText = (TextView) findViewById(R.id.nRepsText);
        nReps = (TextView) findViewById(R.id.nReps);
        toner = MediaPlayer.create(this, R.raw.beep);
        activityEnded = false;

        nRepsText.setVisibility(View.INVISIBLE);
        nReps.setVisibility(View.INVISIBLE);

        setRepsText();
        if(reps < 0)
        {
            nRepsText.setText("Reps:");
        }
        else
        {
            nRepsText.setText("Reps Left:");
        }

        backButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        goBack(v);
                    }
                }
        );

        ready = new Runnable()
        {
            @Override
            public void run()
            {
                if(activityEnded)
                    return;
                intervalName.setText("Ready...");
                activityTimer.setText("2");
                toRun = set;
                tone();
                timerHandler.postDelayed(toRun, 1000);
            }
        };

        set = new Runnable()
        {
            @Override
            public void run()
            {
                if(activityEnded)
                    return;
                intervalName.setText("Set...");
                activityTimer.setText("1");
                toRun = go;
                tone();
                timerHandler.postDelayed(toRun, 1000);

            }
        };

        go = new Runnable()
        {
            @Override
            public void run()
            {
                if(activityEnded)
                    return;
                intervalName.setText(mainTitle);
                activityTimer.setText(String.valueOf(mainTime));
                nRepsText.setVisibility(View.VISIBLE);
                nReps.setVisibility(View.VISIBLE);
                toRun = active;
                tone();
                timerHandler.postDelayed(toRun, 1000);
            }
        };

        active = new Runnable()
        {
            @Override
            public void run()
            {
                if(activityEnded)
                    return;
                if(!decrementTimer())
                {
                    if(restTime > 0)
                    {
                        activityTimer.setText(String.valueOf(restTime));
                        intervalName.setText(secondaryTitle);
                        toRun = rest;
                        tone();
                    }
                    else
                    {
                        activityTimer.setText(String.valueOf(mainTime));
                        reps--;
                        setRepsText();
                        tone();
                    }
                }
                timerHandler.postDelayed(toRun, 1000);
            }
        };

        rest = new Runnable()
        {
            @Override
            public void run()
            {
                if(activityEnded)
                    return;
                if(!decrementTimer())
                {
                    activityTimer.setText(String.valueOf(mainTime));
                    intervalName.setText(mainTitle);
                    reps--;
                    setRepsText();
                    if(reps == 0)
                    {
                        goBack(null);
                    }
                    toRun = active;
                    tone();
                }
                timerHandler.postDelayed(toRun, 1000);
            }
        };
        timerHandler.post(ready);
    }

    private void goBack(View v)
    {
        activityEnded = true;
        this.finish();
    }

    private boolean decrementTimer()
    {
        int curVal = Integer.parseInt(activityTimer.getText().toString());
        if(curVal == 1)
        {
            return false;
        }
        activityTimer.setText(String.valueOf(curVal - 1));
        return true;
    }

    private void tone()
    {
        toner.start();
    }

    private void setRepsText()
    {
        nReps.setText(String.valueOf(Math.abs(reps)));
    }
}
