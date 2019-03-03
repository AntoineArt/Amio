package com.example.artillan1u.amio;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import com.example.artillan1u.amio.R;

public class DatePickerPreference extends DialogPreference {


    public DatePickerPreference(Context ctxt, AttributeSet attrs) {
        super(ctxt, attrs);

        //setPositiveButtonText(ctxt.getText(R.string.cwac_colormixer_set));
        //setNegativeButtonText(ctxt.getText(R.string.cwac_colormixer_cancel));
    }

    @Override
    protected View onCreateDialogView() {
        //mixer=new ColorMixer(getContext());
        //return(mixer);
        DatePicker dp = new DatePicker(getContext());
        return dp;
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);

        //mixer.setColor(lastColor);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        //if (positiveResult) {
        //    if (callChangeListener(mixer.getColor())) {
        //        lastColor=mixer.getColor();
        //        persistInt(lastColor);
        //    }
        //}
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        //return(a.getInt(index, 0xFFA4C639));
        return null;
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        //lastColor=(restoreValue ? getPersistedInt(lastColor) : (Integer)defaultValue);
    }
}