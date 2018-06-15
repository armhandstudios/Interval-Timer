package com.armhandstudios.apps.intervaltimer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity
{
    Button startButton;
    SetIntervalTime primaryTimer;
    SetIntervalTime secondaryTimer;
    RepsFragment repsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startTimerButton);
        primaryTimer = (SetIntervalTime) getSupportFragmentManager().findFragmentById(R.id.mainTimerFragment);
        secondaryTimer = (SetIntervalTime) getSupportFragmentManager().findFragmentById(R.id.secondaryTimerFragment);
        repsFragment = (RepsFragment) getSupportFragmentManager().findFragmentById(R.id.repsFragment);
        primaryTimer.setCanBeZero(false);
        primaryTimer.setActivityNameText("Activity");
        secondaryTimer.setCanBeZero(true);
        secondaryTimer.setActivityNameText("Rest");
        startButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        runTimer(v);
                    }
                }
        );
    }

    private void runTimer(final View v)
    {
        Intent toTimer = new Intent(v.getContext(), TimerRunning.class);
        Bundle bundle = new Bundle();
        bundle.putString("mainActivityTitle", primaryTimer.getActivityNameText());
        bundle.putInt("mainTime", primaryTimer.getTimerValue());
        bundle.putString("secondaryActivityTitle", secondaryTimer.getActivityNameText());
        bundle.putInt("restTime", secondaryTimer.getTimerValue());
        bundle.putInt("nReps", repsFragment.getReps());
        toTimer.putExtras(bundle);
        startActivity(toTimer);
    }
}

//TODO: Maybe change background color and move stuff around, make it look nicer?
