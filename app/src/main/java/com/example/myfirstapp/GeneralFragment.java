package net.oneui.settings.clone;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import dev.oneuiproject.oneui.widget.Toast;

public class GeneralFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.general_management, arg1);
        Preference pref1 = findPreference("pref1");
        Preference pref2 = findPreference("pref2");
        Preference pref3 = findPreference("pref3");
        Preference pref4 = findPreference("pref4");
        Preference pref5 = findPreference("pref5");
        pref1.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                        return true;
                    }
                });
        pref2.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                        return true;
                    }
                });
        pref3.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                        return true;
                    }
                });
        pref4.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(new Intent(Settings.ACTION_CAPTIONING_SETTINGS));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                        return true;
                    }
                });
        pref5.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            Intent intent = new Intent();
                            intent.setClassName(
                                    "com.google.android.apps.subscriptions.red",
                                    "com.google.android.apps.subscriptions.red.main.MainActivity");
                            startActivity(intent);
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "please update or enable the google one app first",
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                        return true;
                    }
                });
    }
}
