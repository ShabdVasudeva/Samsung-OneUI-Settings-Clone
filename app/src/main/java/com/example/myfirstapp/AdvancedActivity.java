package net.oneui.settings.clone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SeslSwitchBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.preference.SwitchPreference;
import dev.oneuiproject.oneui.widget.Toast;
import net.oneui.settings.clone.databinding.ActivityAdvancedBinding;

public class AdvancedActivity extends AppCompatActivity {
    private ActivityAdvancedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdvancedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationButtonAsBack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new AdvancePreference())
                .commit();
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        binding.dark.setChecked(sharedPreferences.getBoolean("value", false));
        binding.dark.addOnSwitchChangeListener(
                new SeslSwitchBar.OnSwitchChangeListener() {
                    @Override
                    public void onSwitchChanged(SwitchCompat arg0, boolean arg1) {
                        if (arg1) {
                            SharedPreferences.Editor editor =
                                    getSharedPreferences("save", MODE_PRIVATE).edit();
                            editor.putBoolean("value", true);
                            editor.apply();
                            binding.dark.setChecked(true);
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        } else {
                            SharedPreferences.Editor editor =
                                    getSharedPreferences("save", MODE_PRIVATE).edit();
                            editor.putBoolean("value", false);
                            editor.apply();
                            binding.dark.setChecked(false);
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        }
                    }
                });
    }
}
