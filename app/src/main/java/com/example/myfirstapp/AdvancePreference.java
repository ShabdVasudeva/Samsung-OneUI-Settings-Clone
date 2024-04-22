package net.oneui.settings.clone;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import dev.oneuiproject.oneui.preference.HorizontalRadioPreference;
import dev.oneuiproject.oneui.widget.Toast;

public class AdvancePreference extends PreferenceFragmentCompat{
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.advance_preferences,arg1);
        Preference pref1 = findPreference("pref1");
        Preference pref2 = findPreference("pref2");
        Preference pref3 = findPreference("pref3");
        pref1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                try {
                	Intent tune = new Intent();
                    tune.setClassName("com.android.systemui",
                    "com.android.systemui.DemoMode");
                        startActivity(tune);
                } catch(Exception err) {
                	Toast.makeText(getActivity().getApplicationContext(),"an error occured",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        pref2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                try {
                	startActivity(new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS));
                } catch(Exception err) {
                	Toast.makeText(getActivity().getApplicationContext(),"an error occured",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                try {
                	startActivity(new Intent(getActivity().getApplicationContext(),BatteryActivity.class));
                } catch(Exception err) {
                	Toast.makeText(getActivity().getApplicationContext(),"an error occured",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
