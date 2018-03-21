package zisad.android.com.notification;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.usage.NetworkStats;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder n;
    public static final int UID = 1009;
    SharedPreferences.Editor sharedEditor;
    SharedPreferences sharedPreferences;

    Button currentNotificationButton, laterNotificationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n=new NotificationCompat.Builder(this);

        sharedPreferences = getSharedPreferences("Notification",Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();

        currentNotificationButton=(Button)findViewById(R.id.currentnotification);
        laterNotificationButton=(Button)findViewById(R.id.laternotification);

        currentNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permission();
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void notification(){
        n.setSmallIcon(R.drawable.zisad);
        n.setContentTitle("Welcome to your Notification");
        n.setContentText("Tap to open apps");
        n.setWhen(System.currentTimeMillis());
        //n.setAutoCancel(true);

        Intent i = new Intent(this,MainActivity.class);
        TaskStackBuilder s = TaskStackBuilder.create(this);
        s.addParentStack(MainActivity.class);
        s.addNextIntent(i);
        PendingIntent p = s.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        n.setContentIntent(p);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(UID,n.build());

        Toast.makeText(MainActivity.this, "Check Notificaiton Bar", Toast.LENGTH_SHORT).show();
    }


    public void permission(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Nitification !!!");
        alert.setMessage("Do you want to get  Notification ??");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notification();
                    }
                }
        );

        alert.create();
        alert.show();
    }

}
