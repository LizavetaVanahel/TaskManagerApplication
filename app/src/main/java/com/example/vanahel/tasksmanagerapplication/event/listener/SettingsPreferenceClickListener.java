package com.example.vanahel.tasksmanagerapplication.event.listener;

import android.preference.CheckBoxPreference;
import android.preference.Preference;

import java.util.List;

public class SettingsPreferenceClickListener implements Preference.OnPreferenceClickListener {

    private List<CheckBoxPreference> checkBoxPreferenceList;

    public SettingsPreferenceClickListener (List<CheckBoxPreference> checkBoxPreferenceList){
        this.checkBoxPreferenceList = checkBoxPreferenceList;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        for (CheckBoxPreference checkBoxPreference : checkBoxPreferenceList) {
            if (!checkBoxPreference.getKey().equals(preference.getKey()) && checkBoxPreference.isChecked()) {
                checkBoxPreference.setChecked(false);
            }
            else if (checkBoxPreference.getKey().equals(preference.getKey()) && !checkBoxPreference.isChecked()) {
                checkBoxPreference.setChecked(true);
            }
        }
        return false;
    }
}


