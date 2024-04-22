package net.oneui.settings.clone;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SowftwareInfo extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.software_info,arg1);
        Preference pref1 = findPreference("pref1");
        Preference pref2 = findPreference("pref2");
        Preference pref3 = findPreference("pref3");
        Preference pref4 = findPreference("pref4");
        Preference pref5 = findPreference("pref5");
        Preference pref6 = findPreference("pref6");
        String version = System.getProperty("os.version");
        pref2.setSummary(Build.VERSION.RELEASE);
        pref2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                Intent intent = new Intent("com.android.internal.app.PLATLOGOACTIVITY");
                startActivity(intent);
                return true;
            }
        });
        pref3.setSummary(Build.VERSION.SECURITY_PATCH);
        pref4.setSummary(Build.getRadioVersion());
        pref5.setSummary(version);
        pref6.setSummary(Build.HARDWARE);
    }
}
