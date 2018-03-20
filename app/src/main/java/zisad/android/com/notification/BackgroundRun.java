package zisad.android.com.notification;

import android.app.Application;
import android.content.Intent;

/**
 * Created by শরীফ নুর জিসাদ on 3/21/2018.
 */
public class BackgroundRun extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, BackgroundService.class));
    }
}
