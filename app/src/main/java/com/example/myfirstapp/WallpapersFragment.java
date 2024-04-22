package net.oneui.settings.clone;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import dev.oneuiproject.oneui.widget.Toast;

public class WallpapersFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle arg0, String arg1) {
        setPreferencesFromResource(R.xml.wallpaper_style, arg1);
        Preference pref1 = findPreference("pref1");
        Preference pref2 = findPreference("pref2");
        Preference pref3 = findPreference("pref3");
        pref1.setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        try {
                            startActivity(
                                    new Intent(
                                            getActivity().getApplicationContext(),
                                            Wallpapers.class));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "an error occured",
                                            Toast.LENGTH_LONG)
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
                            Intent intent = new Intent();
                            intent.setComponent(
                                    new ComponentName(
                                            "dev.kdrag0n.dyntheme",
                                            "dev.kdrag0n.dyntheme.ui.main.MainActivity"));
                            startActivity(intent);
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "please install repainter app first",
                                            Toast.LENGTH_LONG)
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
                            Intent intent = new Intent();
                            intent.setComponent(
                                    new ComponentName(
                                            "com.sec.android.app.launcher",
                                            "com.android.homescreen.settings.HomeModeChangeActivity"));
                            startActivity(intent);
                        } catch (Exception err) {
                            Toast.makeText(
                                            getActivity().getApplicationContext(),
                                            "please install our oneui home app port first",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                        return true;
                    }
                });
    }
}
