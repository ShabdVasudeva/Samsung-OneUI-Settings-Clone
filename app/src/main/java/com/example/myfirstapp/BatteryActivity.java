package net.oneui.settings.clone;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import dev.oneuiproject.oneui.widget.Toast;
import java.util.stream.Stream;
import net.oneui.settings.clone.databinding.ActivityBatteryBinding;

public class BatteryActivity extends AppCompatActivity {
    public static int REQUEST_MODIFY_SYSTEM_SETTINGS = 1;
    public ActivityBatteryBinding binding;
    private BroadcastReceiver mBatInfoReceiver =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context ctxt, Intent intent) {
                    try {
                        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                        int batteryPct = level * 100 / (int) scale;
                        String technology =
                                intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
                        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
                        int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                    } catch (Exception ig) {
                        Toast.makeText(
                                getApplicationContext(), "an error occured", Toast.LENGTH_SHORT);
                    }
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBatteryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationButtonAsBack();
        int nightmode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch(nightmode){
            case Configuration.UI_MODE_NIGHT_YES:
        }
        registerReceiver(this.mBatInfoReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryScale = this.registerReceiver(null,ifilter);
        Intent batteryLevel = this.registerReceiver(null,ifilter);
        Intent batteryStatus = this.registerReceiver(null, ifilter);
        int scale = batteryScale != null ? batteryScale.getIntExtra(BatteryManager.EXTRA_SCALE,-1):-1;
        int level = batteryLevel != null ? batteryLevel.getIntExtra(BatteryManager.EXTRA_LEVEL,-1):-1;
        int batteryPct = level * 100 / (int) scale;
        binding.percentage.setText(String.valueOf(batteryPct) + "%" + " " + "available");
        binding.progress.setProgress(batteryPct);
        showBatteryEstimation(getApplicationContext());
          int status =
                    batteryStatus != null
                            ? batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                            : -1;
            boolean isCharging =
                    status == BatteryManager.BATTERY_STATUS_CHARGING
                            || status == BatteryManager.BATTERY_STATUS_FULL;
            if (isCharging) {
                binding.status.setText("Charging");
            } else{
                binding.status.setText("discharging");
            }
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container,new BatteryPreference()).commit();
    }
    public void showBatteryEstimation(Context context) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, filter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        int batteryPercent = (int) ((level / (float) scale) * 100);
        int remainingTimeHours = batteryPercent;

        String message = remainingTimeHours + " hours";
        binding.advice.setText(message);
    }
}
