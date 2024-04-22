package net.oneui.settings.clone;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import dev.oneuiproject.oneui.widget.Toast;
import net.oneui.settings.clone.databinding.LayoutPhoneInfoBinding;

public class PhoneInfo extends AppCompatActivity {
    public LayoutPhoneInfoBinding binding;
    private static final int REQUEST_READ_PHONE_STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutPhoneInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationButtonAsBack();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.READ_PHONE_STATE},
                    REQUEST_READ_PHONE_STATE);
        } else {
            getAndSetSimCardName();
        }
        binding.deviceName.setText(Settings.Global.getString(getContentResolver(), "device_name"));
        binding.device.setText(Settings.Global.getString(getContentResolver(), "device_name"));
        binding.model.setText(Build.MODEL);
        binding.backup.setOnClickListener(
                v -> {
                    try {
                        Intent ig = new Intent();
                        ig.setClassName(
                                "com.google.android.apps.subscriptions.red",
                                "com.google.android.apps.subscriptions.red.main.MainActivity");
                        startActivity(ig);
                    } catch (Exception i) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "please install or enable google one application",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.update.setOnClickListener(v ->{
            try {
            	startActivity(new Intent("android.settings.SYSTEM_UPDATE_SETTINGS"));
            } catch(Exception err) {
            	Toast.makeText(getApplicationContext(),"an error occured",Toast.LENGTH_SHORT).show();
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new AboutPreferences())
                .commit();
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAndSetSimCardName();
            }
        }
    }

    private void getAndSetSimCardName() {
        TelephonyManager telephonyManager =
                (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            String simOperatorName = telephonyManager.getSimOperatorName();
            binding.sim.setText(simOperatorName);
        }
    }
}
