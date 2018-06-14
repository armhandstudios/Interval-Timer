package com.armhandstudios.apps.intervaltimer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Timer extends Fragment
{
    boolean canBeZero;

    Button decrementTimer;
    Button incrementTimer;
    TextView timerCount;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        canBeZero = false;

        decrementTimer = (Button) view.findViewById(R.id.decrementTimer);
        incrementTimer = (Button) view.findViewById(R.id.incrementTimer);
        timerCount = (TextView) view.findViewById(R.id.timerCounts);

        decrementTimer.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        decrementClicked(v);

                    }
                }
        );
        incrementTimer.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        incrementClicked(v);
                    }

                }
        );
        //The following code can be used to sanitize the text fields if they are editable. Ultimately,
        //I decided against having them be directly editable, so this code is irrelevant
        /*
        timerCount.setOnFocusChangeListener(
                new View.OnFocusChangeListener()
                {
                    public void onFocusChange(View v, boolean hasFocus)
                    {
                        if(!canBeZero && Integer.parseInt(timerCount.getText().toString()) == 0)
                        {
                            timerCount.setText("1");
                        }
                    }
                }
        );*/
        return view;
    }

    public void decrementClicked(View view)
    {
        int curVal = Integer.parseInt(timerCount.getText().toString());
        if(curVal == 1 && !canBeZero)
        {
            return;
        }
        timerCount.setText(String.valueOf(curVal - 1));
    }

    public void incrementClicked(View view)
    {
        int curVal = Integer.parseInt(timerCount.getText().toString());
        timerCount.setText(String.valueOf(curVal + 1));
    }

    public int getTimerValue()
    {
        return Integer.parseInt(timerCount.getText().toString());
    }

    public void setCanBeZero(boolean b)
    {
        canBeZero = b;
    }
}