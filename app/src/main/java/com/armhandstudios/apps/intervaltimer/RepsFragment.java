package com.armhandstudios.apps.intervaltimer;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepsFragment extends Fragment
{
    int prevValue;

    CheckBox infiniteReps;
    EditText nReps;
    TextView repsText;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_reps, container, false);

        prevValue = 10;
        infiniteReps = (CheckBox) view.findViewById(R.id.useRepsCheck);
        nReps = (EditText) view.findViewById(R.id.enterReps);
        repsText = (TextView) view.findViewById(R.id.enterRepsText);

        infiniteReps.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        nReps.setEnabled(!isChecked);
                        repsText.setEnabled(!isChecked);

                    }
                }
        );
        nReps.setOnFocusChangeListener(
                new View.OnFocusChangeListener()
                {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus)
                    {
                        if(hasFocus)
                        {
                            prevValue = getReps();
                        }
                        else
                        {
                            if(nReps.getText().toString().equals("") || Integer.valueOf(nReps.getText().toString()) == 0)
                            {
                                nReps.setText(String.valueOf(prevValue));
                            }
                        }
                    }
                }
        );

        return view;
    }

    public int getReps()
    {
        if(infiniteReps.isChecked())
        {
            return -1;
        }
        else
        {
            return Integer.valueOf(nReps.getText().toString());
        }
    }

}
