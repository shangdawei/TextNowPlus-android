package org.cyanogenmod.voiceplus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/**
 * Created by koush on 8/21/13.
 */
public class VoiceListenerService extends NotificationListenerService {
    SharedPreferences settings;
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (!Helper.API_SMS_PACKAGE.equals(sbn.getPackageName()))
            return;
        if (settings == null)
            settings = getSharedPreferences("settings", MODE_PRIVATE);
        if (null == settings.getString("account", null))
            return;
        cancelNotification(Helper.API_SMS_PACKAGE, sbn.getTag(), sbn.getId());
        startService(new Intent(this, VoicePlusService.class).setAction(VoicePlusService.ACTION_INCOMING_API_SMS));
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }
}
