package net.oneui.settings.clone;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import net.oneui.settings.clone.databinding.ActivitySoftwareBinding;

public class SoftwareActivity extends AppCompatActivity {
    public ActivitySoftwareBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySoftwareBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationButtonAsBack();
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container,new SowftwareInfo()).commit();
    }
}
