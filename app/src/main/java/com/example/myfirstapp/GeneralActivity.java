package net.oneui.settings.clone;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import dev.oneuiproject.oneui.widget.Toast;
import net.oneui.settings.clone.databinding.ActivityGeneralBinding;

public class GeneralActivity extends AppCompatActivity {
    private ActivityGeneralBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGeneralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationButtonAsBack();
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container,new GeneralFragment()).commit();
    }
}
