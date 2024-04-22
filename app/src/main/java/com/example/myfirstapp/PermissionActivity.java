package net.oneui.settings.clone;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import dev.oneuiproject.oneui.widget.Toast;
import net.oneui.settings.clone.databinding.ActivityPermissionBinding;

public class PermissionActivity extends AppCompatActivity {
    private ActivityPermissionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPermissionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationButtonAsBack();
        setValue(binding.modify,new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS));
        setValue(binding.usage,new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        setValue(binding.files,new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION));
        setValue(binding.unknown,new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES));
        setValue(binding.overlay,new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
        setValue(binding.notifications,new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
    }
    public void setValue(LinearLayout layout,Intent intent){
        layout.setOnClickListener(v ->{
             try {
             	startActivity(intent);
             } catch(Exception err) {
             	Toast.makeText(getApplicationContext(),"An error occured",Toast.LENGTH_SHORT).show();
             }   
        });
    }
}
