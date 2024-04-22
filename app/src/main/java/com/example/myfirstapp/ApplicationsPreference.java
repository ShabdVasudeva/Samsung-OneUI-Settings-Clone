package net.oneui.settings.clone;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class ApplicationsPreference extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.applications_preference,arg1);
        Preference pref1 = findPreference("pref1");
        Preference pref2 = findPreference("pref2");
        Preference pref3 = findPreference("pref3");
        Preference pref4 = findPreference("pref4");
        Preference pref5 = findPreference("pref5");
        pref1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                return true;
            }
        });
        pref2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                startActivity(new Intent(Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS));
                return true;
            }
        });
        pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                startActivity(new Intent(Settings.ACTION_HOME_SETTINGS));
                return true;
            }
        });
        pref4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                startActivity(new Intent(Settings.ACTION_VOICE_INPUT_SETTINGS));
                return true;
            }
        });
        pref5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                startActivity(new Intent(getActivity().getApplicationContext(),PermissionActivity.class));
                return true;
            }
        });
    }
}
