package net.oneui.settings.clone;

import androidx.lifecycle.ViewModel;
import dev.oneuiproject.oneui.R;

public class ModelViewMain extends ViewModel{
    String[]titles={
        "Connections",
        "Connected devices",
        "Sound and vibration",
        "Notifications",
        "Display",
        "Battery",
        "Wallpaper and style",
        "Home screen",
        "Security",
        "Privacy",
        "Location",
        "Printing service",
        "Accounts and backup",
        "Advanced features",
        "Apps",
        "Accessibility"
    };
    String[]subtitles={
        "Wifi • hotspot • mobile",
        "Bluetooth • pairing",
        "Volume • haptics • ringtone",
        "Status bar • dnd",
        "Brightness • eye comfort • dark theme",
        "Power saving • charging",
        "Wallpapers • colour palette",
        "Layout • app icon badges",
        "Screen lock • find my devices • app security",
        "Permission • personal data • account activity",
        "Location • apps using gps",
        "Default printing service",
        "Manage accounts • data synchronisation",
        "Labs",
        "Default apps • app settings",
        "Display • interaction • audio"
    };
    int[]icons={
        R.drawable.ic_oui_category_connections,
        R.drawable.ic_oui_category_galaxy_watch,
        R.drawable.ic_oui_category_sounds,
        R.drawable.ic_oui_category_notifications,
        R.drawable.ic_oui_category_display,
        R.drawable.ic_oui_category_battery,
        net.oneui.settings.clone.R.drawable.qo,
        R.drawable.ic_oui_category_effect,
        R.drawable.ic_oui_category_lockscreen,
        R.drawable.ic_oui_category_security,
        R.drawable.ic_oui_category_location,
        R.drawable.ic_oui_category_smartthings,
        R.drawable.ic_oui_category_accounts_backup,
        R.drawable.ic_oui_category_advanced_options,
        R.drawable.ic_oui_category_apps,
        R.drawable.ic_oui_category_accessibility
    };
}