package net.oneui.settings.clone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import net.oneui.settings.clone.databinding.ActivityAboutBinding;
import dev.oneuiproject.oneui.widget.Toast;

public class AboutActivity extends AppCompatActivity {
    private ActivityAboutBinding about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        about = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(about.getRoot());
        about.aboutSourceCode.setOnClickListener(
                v -> {
                    Toast.makeText(
                                    this,
                                    "to get source code you should contact the developer",
                                    Toast.LENGTH_SHORT)
                            .show();
                    startActivity(
                            new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://t.me/AndroidPortWorldDiscussion")));
                });
        about.aboutDonate.setOnClickListener(
                v -> {
                    Toast.makeText(this, "please join us on telegram", Toast.LENGTH_LONG).show();
                    startActivity(
                            new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://t.me/AndroidPortWorld")));
                });
    }
}
