package net.oneui.settings.clone;

import android.content.ComponentName;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dev.oneuiproject.oneui.widget.Toast;
import net.oneui.settings.clone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ModelViewMain util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setValue(binding.txt1, binding.subtxt1, binding.icon1, 0, 0, 0);
        setValue(binding.txt2, binding.subtxt2, binding.icon2, 1, 1, 1);
        setValue(binding.txt3, binding.subtxt3, binding.icon3, 2, 2, 2);
        setValue(binding.txt4, binding.subtxt4, binding.icon4, 3, 3, 3);
        setValue(binding.txt5, binding.subtxt5, binding.icon5, 4, 4, 4);
        setValue(binding.txt6, binding.subtxt6, binding.icon6, 5, 5, 5);
        setValue(binding.txt7, binding.subtxt7, binding.icon7, 6, 6, 6);
        setValue(binding.txt8, binding.subtxt8, binding.icon8, 7, 7, 7);
        setValue(binding.txt9, binding.subtxt9, binding.icon9, 8, 8, 8);
        setValue(binding.txt10, binding.subtxt10, binding.icon10, 9, 9, 9);
        setValue(binding.txt11, binding.subtxt11, binding.icon11, 10, 10, 10);
        setValue(binding.txt12, binding.subtxt12, binding.icon12, 11, 11, 11);
        setValue(binding.txt13, binding.subtxt13, binding.icon13, 12, 12, 12);
        setValue(binding.txt14, binding.subtxt14, binding.icon14, 13, 13, 13);
        setValue(binding.txt17, binding.subtxt17, binding.icon17, 14, 14, 14);
        setValue(binding.txt19, binding.subtxt19, binding.icon19, 15, 15, 15);
        setAction(binding.connections, new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        setAction(binding.connectedDevices, new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
        setAction(binding.sounds, new Intent(Settings.ACTION_SOUND_SETTINGS));
        setAction(
                binding.notifications, new Intent(Settings.ACTION_ALL_APPS_NOTIFICATION_SETTINGS));
        setAction(binding.display, new Intent(Settings.ACTION_DISPLAY_SETTINGS));
        setAction(binding.battery, new Intent(this, BatteryActivity.class));
        setAction(binding.wallsStyles, new Intent(this, WallpapersStyles.class));
        binding.homeScreen.setOnClickListener(
                v -> {
                    Intent ig = new Intent();
                });
        Intent intent = new Intent();
        intent.setComponent(
                new ComponentName(
                        "com.sec.android.app.launcher",
                        "com.android.homescreen.settings.HomeModeChangeActivity"));
        binding.homeScreen.setOnClickListener(v ->{
            try {
            	startActivity(intent);
            } catch(Exception err) {
            	Toast.makeText(getApplicationContext(),"Please install our oneui home port",Toast.LENGTH_LONG).show();
            }
        });
        setAction(binding.security, new Intent(Settings.ACTION_SECURITY_SETTINGS));
        setAction(binding.privacy, new Intent(Settings.ACTION_PRIVACY_SETTINGS));
        setAction(binding.location, new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        setAction(binding.print, new Intent(Settings.ACTION_PRINT_SETTINGS));
        setAction(binding.accounts, new Intent(Settings.ACTION_SYNC_SETTINGS));
        setAction(binding.features, new Intent(this, AdvancedActivity.class));
        binding.wellbeing.setOnClickListener(
                v -> {
                    Intent wellb = new Intent();
                    wellb.setClassName(
                            "com.google.android.apps.wellbeing",
                            "com.google.android.apps.wellbeing.settings.TopLevelSettingsActivity");
                    try {
                        startActivity(wellb);
                    } catch (Exception e) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "sorry your device not support this setting don't worry our team is working for you",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.care.setOnClickListener(
                v -> {
                    try {
                        Intent ig = new Intent();
                        ig.setClassName(
                                "com.sesl.device.care", "com.sesl.device.care.MainActivity");
                        startActivity(ig);
                    } catch (Exception io) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "please wait for pur device care app release",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        setAction(binding.apps, new Intent(this, AppsActivity.class));
        setAction(binding.management, new Intent(this, GeneralActivity.class));
        setAction(binding.accessibility, new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        setAction(binding.about, new Intent(this, PhoneInfo.class));
    }

    public void setValue(
            TextView title,
            TextView subtitle,
            ImageView image,
            Integer first,
            Integer second,
            Integer third) {
        util = new ViewModelProvider(this).get(ModelViewMain.class);
        title.setText(util.titles[first]);
        subtitle.setText(util.subtitles[second]);
        image.setImageResource(util.icons[third]);
    }

    public void setAction(LinearLayout layout, Intent intent) {
        util = new ViewModelProvider(this).get(ModelViewMain.class);
        layout.setOnClickListener(
                v -> {
                    try {
                        startActivity(intent);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplication(),
                                        "our team is trying hard to fix it.",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(net.oneui.settings.clone.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == net.oneui.settings.clone.R.id.search) {
            try {
                startActivity(new Intent(Settings.ACTION_APP_SEARCH_SETTINGS));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } catch (Exception io) {
                Toast.makeText(
                                getApplicationContext(),
                                "our team is working hard to fix this error",
                                Toast.LENGTH_SHORT)
                        .show();
            }
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
