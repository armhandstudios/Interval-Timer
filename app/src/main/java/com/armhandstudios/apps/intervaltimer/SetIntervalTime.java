package com.armhandstudios.apps.intervaltimer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class SetIntervalTime extends Fragment
{
    boolean canBeZero;
    int prevValue;

    EditText activityNameText;
    EditText timerCount;
    TextView unitName;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_set_interval_time, container, false);

        canBeZero = true;
        prevValue = 30;

        activityNameText = (EditText) view.findViewById(R.id.activityNameText);
        timerCount = (EditText) view.findViewById(R.id.timerCount);
        unitName = (TextView) view.findViewById(R.id.timeUnitsText);

        activityNameText.setOnFocusChangeListener(
                new View.OnFocusChangeListener()
                {
                    public void onFocusChange (View v, boolean hasFocus)
                    {
                        if(hasFocus)
                        {
                            activityNameText.setCursorVisible(true);
                        }
                        else
                        {
                            if(activityNameText.getText().toString().equals(""))
                            {
                                activityNameText.setHint("Activity Name");
                            }
                            else
                            {
                                activityNameText.setHint(null);
                            }
                            activityNameText.setCursorVisible(false);
                        }
                    }
                }
        );

        timerCount.setOnFocusChangeListener(
                new View.OnFocusChangeListener()
                {
                    public void onFocusChange(View v, boolean hasFocus)
                    {
                        if(hasFocus)
                        {
                            timerCount.setCursorVisible(true);
                            prevValue = getTimerValue();
                        }
                        else
                        {
                            timerCount.setCursorVisible(false);
                        }
                        if(timerCount.getText().toString().equals(""))
                        {
                            timerCount.setText(String.valueOf(prevValue));
                        }
                        if(!canBeZero && Integer.parseInt(timerCount.getText().toString()) == 0)
                        {
                            timerCount.setText("1");
                        }
                    }
                }
        );

        return view;
    }

    public void setCanBeZero(boolean b)
    {
        canBeZero = b;
    }

    public String getActivityNameText()
    {
        return activityNameText.getText().toString();
    }

    public void setActivityNameText(String s)
    {
        activityNameText.setText(s);
    }

    public int getTimerValue()
    {
        return Integer.parseInt(timerCount.getText().toString());
    }
}
