package net.oneui.settings.clone;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;
import dev.oneuiproject.oneui.widget.Toast;
import java.util.List;

public class BatteryPreference extends PreferenceFragmentCompat {
    public Preference pct,temp,tech,hlth;
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.preferences, arg1);
        SwitchPreferenceCompat pref1 = findPreference("key1");
        pref1.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            Settings.System.putInt(
                                    getActivity().getContentResolver(),
                                    Settings.System.SCREEN_BRIGHTNESS,
                                    1);
                            ActivityManager activityManager =
                                    (ActivityManager)
                                            getActivity()
                                                    .getApplicationContext()
                                                    .getSystemService(Context.ACTIVITY_SERVICE);
                            List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo =
                                    activityManager.getRunningAppProcesses();
                            for (ActivityManager.RunningAppProcessInfo processInfo :
                                    runningAppProcessInfo) {
                                if (!processInfo.processName.equals(
                                        getActivity().getApplicationContext().getPackageName())) {
                                    activityManager.killBackgroundProcesses(
                                            processInfo.processName);
                                }
                            }
                        } else {
                            Settings.System.putInt(
                                    getActivity().getContentResolver(),
                                    Settings.System.SCREEN_BRIGHTNESS,
                                    0);
                        }
                        return true;
                    }
                });
        SwitchPreferenceCompat pref2 = findPreference("key2");
        pref2.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference arg0, Object arg1) {
                        Boolean isChecked = (Boolean) arg1;
                        if (isChecked) {
                            ActivityManager activityManager =
                                    (ActivityManager)
                                            getActivity()
                                                    .getApplicationContext()
                                                    .getSystemService(Context.ACTIVITY_SERVICE);
                            List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo =
                                    activityManager.getRunningAppProcesses();
                            for (ActivityManager.RunningAppProcessInfo runningProcess :
                                    runningAppProcessInfo) {
                                if (!runningProcess.processName.equals(
                                        getActivity().getApplicationContext().getPackageName())) {
                                    activityManager.killBackgroundProcesses(
                                            runningProcess.processName);
                                }
                            }
                        } else {

                        }
                        return true;
                    }
                });
        Preference pref3 = findPreference("pref3");
        pref3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                try {
                	startActivity(new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS));
                } catch(Exception err) {
                	Toast.makeText(getActivity().getApplicationContext(),"an error occured",Toast.LENGTH_SHORT).show();
                }    
                return true;
            }
        });
    }
    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        pct = findPreference("pct");
        temp = findPreference("temp");
        tech = findPreference("tech");
        hlth = findPreference("hlth");
        BatteryReciever batteryReciever = new BatteryReciever();
        getActivity().registerReceiver(batteryReciever,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent levelBat = getActivity().registerReceiver(null,ifilter);
        Intent scaleBat = getActivity().registerReceiver(null,ifilter);
        Intent tempBat = getActivity().registerReceiver(null,ifilter);
        Intent techBat = getActivity().registerReceiver(null,ifilter);
        Intent hlthBat = getActivity().registerReceiver(null,ifilter);
        String technology = techBat != null ? techBat.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY):null;
        int health = hlth != null ? hlthBat.getIntExtra(BatteryManager.EXTRA_HEALTH,-1):-1;
        int temperature = tempBat != null ? tempBat.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1):-1;
        int level = levelBat != null ? levelBat.getIntExtra(BatteryManager.EXTRA_LEVEL,-1):-1;
        int scale = scaleBat != null ? scaleBat.getIntExtra(BatteryManager.EXTRA_SCALE,-1):-1;
        int batteryPct = level * 100 / (int) scale;
        pct.setSummary(String.valueOf(batteryPct)+"%" +" "+"available");
        temp.setSummary(String.valueOf(temperature / 10.0)+"° c");
        tech.setSummary(technology);
        if (health == BatteryManager.BATTERY_HEALTH_COLD) {
            hlth.setSummary("cold");
        } else if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
            hlth.setSummary("Good");
        } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
            hlth.setSummary("Dead");
        } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
            hlth.setSummary("Over Voltage");
        } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
            hlth.setSummary("Over Heating");
        } else {
            hlth.setSummary("Unknown");
        }
    }
}
