package net.oneui.settings.clone;

import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import net.oneui.settings.clone.databinding.LayoutWallpapersBinding;
import dev.oneuiproject.oneui.widget.Toast;
import java.io.IOException;

public class Wallpapers extends AppCompatActivity {
    private LayoutWallpapersBinding wall;
    private static final int PICK_IMAGE_REQUEST = 1;

    private int colorToString(String color) {
        return Color.parseColor(color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wall = LayoutWallpapersBinding.inflate(getLayoutInflater());
        setContentView(wall.getRoot());
        wall.toolbar.setNavigationButtonAsBack();
        wall.gallery.setOnClickListener(
                v -> {
                    openImagePicker();
                });
        wall.setWall.setOnClickListener(
                v -> {
                    try {
                        String str1 = wall.edit.getText().toString();
                        String str2 = wall.edit2.getText().toString();
                        int a = colorToString(str1);
                        int b = colorToString(str2);
                        setWallpaperWithGradientColors(a, b);
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "An Error Occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

    private void onWallpaperChange(Bitmap bom) {
        WallpaperManager walli = WallpaperManager.getInstance(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set wallpaper as")
                .setNegativeButton(
                        "Lockscreen",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    walli.setBitmap(bom, null, false, WallpaperManager.FLAG_LOCK);
                                    success();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    fail();
                                }
                            }
                        })
                .setPositiveButton(
                        "Homescreen",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    walli.setBitmap(bom, null, false, WallpaperManager.FLAG_SYSTEM);
                                    success();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    fail();
                                }
                            }
                        })
                .setNeutralButton(
                        "Both",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    walli.setBitmap(bom, null, false, WallpaperManager.FLAG_SYSTEM);
                                    walli.setBitmap(bom, null, false, WallpaperManager.FLAG_LOCK);
                                    success();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    fail();
                                }
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void openImagePicker() {
        Intent pickImageIntent =
                new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Set wallpaper as")
                    .setNegativeButton(
                            "Lockscreen",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaperLock(selectedImageUri);
                                }
                            })
                    .setPositiveButton(
                            "Homescreen",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaper(selectedImageUri);
                                }
                            })
                    .setNeutralButton(
                            "Both",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaperBoth(selectedImageUri);
                                }
                            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private void setWallpaper(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_SYSTEM);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void setWallpaperLock(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_LOCK);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void setWallpaperBoth(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(getContentResolver().openInputStream(imageUri));
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void success() {
        Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
    }

    private void fail() {
        Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
    }

    public void setWallpaper(View view) {
        String wallpaperUrl = wall.link.getText().toString();
        new WallpaperAsyncTask().execute(wallpaperUrl);
    }

    private class WallpaperAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            String wallpaperUrl = params[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(wallpaperUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                onWallpaperChange(bitmap);
            } else {
                Toast.makeText(
                                getApplicationContext(),
                                "Failed to download wallpaper",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    
    private Bitmap resizeBitmap(Bitmap bitmap, int targetWidth, int targetHeight) {
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
    }
    
    private void setWallpaperWithGradientColors(int startColor, int endColor) {
        GradientDrawable gradientDrawable =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM, new int[] {startColor, endColor});
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        gradientDrawable.setBounds(0, 0, 100, 100);
        gradientDrawable.draw(new Canvas(bitmap));
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        Bitmap resizedBitmap = resizeBitmap(bitmap, screenWidth, screenHeight);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set wallpaper as")
                .setNegativeButton(
                        "Lockscreen",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(
                                            resizedBitmap, null, true, WallpaperManager.FLAG_LOCK);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setPositiveButton(
                        "Homescreen",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(
                                            resizedBitmap,
                                            null,
                                            true,
                                            WallpaperManager.FLAG_SYSTEM);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setNeutralButton(
                        "Both",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(resizedBitmap);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .show();
    }
}
