package net.oneui.settings.clone;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import net.oneui.settings.clone.databinding.ActivityAppsBinding;

public class AppsActivity extends AppCompatActivity {
    private ActivityAppsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationButtonAsBack();
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container,new ApplicationsPreference()).commit();
    }
}
