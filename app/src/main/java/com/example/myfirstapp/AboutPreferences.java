package net.oneui.settings.clone;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import dev.oneuiproject.oneui.widget.Toast;

public class AboutPreferences extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.preference_about,arg1);
        Preference pref1 = findPreference("software");
        pref1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                try {
                	startActivity(new Intent(getActivity().getApplicationContext(),SoftwareActivity.class));
                } catch(Exception err) {
                	Toast.makeText(getActivity().getApplication(),"an error occured",Toast.LENGTH_SHORT).show();
                } 
                return true;
            }
        });
        Preference pref2 = findPreference("other");
        pref2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                try {
                	startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
                } catch(Exception err) {
                	Toast.makeText(getActivity().getApplication(),"an error occured",Toast.LENGTH_SHORT).show();
                }    
                return true;
            }
        });
        Preference pref3 = findPreference("battery");
        pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                try {
                	startActivity(new Intent(getActivity().getApplicationContext(),BatteryActivity.class));
                } catch(Exception err) {
                	Toast.makeText(getActivity().getApplication(),"an error occured",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
