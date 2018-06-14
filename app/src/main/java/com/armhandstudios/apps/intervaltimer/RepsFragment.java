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


/**
 * A simple {@link Fragment} subclass.
 */
public class RepsFragment extends Fragment
{
    CheckBox infiniteReps;
    EditText nReps;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_reps, container, false);

        infiniteReps = (CheckBox) view.findViewById(R.id.useRepsCheck);
        nReps = (EditText) view.findViewById(R.id.enterReps);

        infiniteReps.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        //can disable edittext here if correct states have been entered in xml
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
