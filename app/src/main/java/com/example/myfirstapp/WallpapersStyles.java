package net.oneui.settings.clone;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.view.MenuInflater;
import androidx.annotation.NonNull;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Canvas;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.app.ActivityCompat;
import androidx.core.view.MenuCompat;
import androidx.lifecycle.ViewModelProvider;
import dev.oneuiproject.oneui.widget.Toast;
import androidx.annotation.Nullable;
import android.app.WallpaperManager;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.media.projection.MediaProjection;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import android.os.Handler;
import net.oneui.settings.clone.R;
import java.io.FileNotFoundException;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import dev.oneuiproject.oneui.layout.ToolbarLayout;
import java.io.IOException;
import net.oneui.settings.clone.databinding.WallpapersStylesBinding;

public class WallpapersStyles extends AppCompatActivity {
    private WallpapersStylesBinding binding;
    private static final int PICK_IMAGE_REQUEST = 1;
    private StylesModel model;
    public static final int REQUEST_PHONE_STORAGE = 1;
    public static final int REQUEST_WRITE_STORAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = WallpapersStylesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationButtonAsBack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new WallpapersFragment())
                .commit();
        binding.lockScreenPreview.setOnClickListener(
                v -> {
                    WallpaperManager walli = WallpaperManager.getInstance(this);
                    binding.lockScreenPreview.buildDrawingCache();
                    Bitmap bmap = binding.lockScreenPreview.getDrawingCache();
                    try {
                        walli.setBitmap(bmap, null, false, WallpaperManager.FLAG_LOCK);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        binding.homeScreenPreview.setOnClickListener(
                v -> {
                    WallpaperManager walli = WallpaperManager.getInstance(this);
                    binding.homeScreenPreview.buildDrawingCache();
                    Bitmap bmap = binding.homeScreenPreview.getDrawingCache();
                    try {
                        walli.setBitmap(bmap, null, false, WallpaperManager.FLAG_SYSTEM);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        binding.liveWallpaper.setOnClickListener(
                v -> {
                    startActivity(new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER));
                });
        binding.phoneSettings.setOnClickListener(
                v -> {
                    Intent intent = new Intent();
                    intent.setComponent(
                            new ComponentName(
                                    "com.samsung.android.app.homestar",
                                    "com.samsung.android.app.homestar.SettingActivity"));
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(this, "Please install HomeUP app first", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        binding.goodLock.setOnClickListener(
                v -> {
                    startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
                });
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        	ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PHONE_STORAGE);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE_STORAGE);
        } else {
        	showScreens();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == REQUEST_PHONE_STORAGE && requestCode == REQUEST_WRITE_STORAGE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            	showScreens();
            }
        }
    }
    public void showScreens() {
        model = new ViewModelProvider(this).get(StylesModel.class);
        try {
            model.getLockScreenWallpaper()
                    .observe(
                            this,
                            lockScreenWallpaper -> {
                                binding.lockScreenPreview.setBackgroundDrawable(
                                        lockScreenWallpaper);
                            });

            model.getHomeScreenWallpaper()
                    .observe(
                            this,
                            homeScreenWallpaper -> {
                                binding.homeScreenPreview.setBackgroundDrawable(
                                        homeScreenWallpaper);
                            });
        } catch (Exception sh) {
            Toast.makeText(
                            getApplicationContext(),
                            "give feedback to the developer about this error please",
                            Toast.LENGTH_SHORT)
                    .show();
        }
        model.retrieveLockScreenWallpaper(this);
        model.retrieveHomeScreenWallpaper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
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
