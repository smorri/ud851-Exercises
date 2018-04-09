package android.example.com.visualizerpreferences;

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Samone Morris
 * @date   04/09/2018 (Re-completed as original file was lost due to motherboard failure)
 */

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.widget.Toast;

// COMPLETED (1) Implement OnPreferenceChangeListener
public class SettingsFragment extends PreferenceFragmentCompat implements
        OnSharedPreferenceChangeListener,
        OnPreferenceChangeListener{

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        // Add visualizer preferences, defined in the XML file in res->xml->pref_visualizer
        addPreferencesFromResource(R.xml.pref_visualizer);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();

        // Go through all of the preferences, and set up their preference summary.
        for (int i = 0; i < count; i++) {
            Preference p = prefScreen.getPreference(i);
            // You don't need to set up preference summaries for checkbox preferences because
            // they are already set up in xml using summaryOff and summary On
            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }
        // COMPLETED (3) Add the OnPreferenceChangeListener specifically to the EditTextPreference
        Resources resources = getResources();
        EditTextPreference editTextPreference =
                (EditTextPreference) prefScreen.findPreference(
                        resources.getString(R.string.pref_size_key)
        );

        if( editTextPreference != null ){
            editTextPreference.setOnPreferenceChangeListener( this );
        }// end if
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Figure out which preference was changed
        Preference preference = findPreference(key);
        if (null != preference) {
            // Updates the summary for the preference
            if (!(preference instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }
    }

    /**
     * Updates the summary for the preference
     *
     * @param preference The preference to be updated
     * @param value      The value that the preference was updated to
     */
    private void setPreferenceSummary(Preference preference, String value) {
        if (preference instanceof ListPreference) {
            // For list preferences, figure out the label of the selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                // Set the summary to that label
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else if (preference instanceof EditTextPreference) {
            // For EditTextPreferences, set the summary to the value's simple string representation.
            preference.setSummary(value);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    // COMPLETED (2) Override onPreferenceChange. This method should try to convert the new preference value to a float; if it cannot, show a helpful error message and return false. If it can be converted to a float check that that float is between 0 (exclusive) and 3 (inclusive). If it isn't, show an error message and return false. If it is a valid number, return true.
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if( preference != null && preference instanceof EditTextPreference ){
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            String previous_value = editTextPreference.getText();

            // Ensure that the value entered can be represented as a Float value. If not display
            // an error message.
            float float_value;

            try {
                float_value = Float.parseFloat( String.valueOf( newValue ) );
            } catch (NumberFormatException e) {
                e.printStackTrace();

                editTextPreference.setText( previous_value );
                editTextPreference.setSummary( previous_value );

                Toast.makeText( getContext(),
                                "Enter a valid number between 1 and 3, inclusive",
                                Toast.LENGTH_LONG )
                .show();
                return false;
            }// end try / catch

            // If the number is not between [1, 3] display an error message
            if( float_value < 1f || float_value > 3f ){
                editTextPreference.setText( previous_value );
                editTextPreference.setSummary( previous_value );

                Toast.makeText( getContext(),
                        "Enter a value between 1 and 3, inclusive",
                        Toast.LENGTH_LONG )
                        .show();

                return false;
            }// end if
        }// end if

        return true;
    }// end onPreferenceChange(...)
}