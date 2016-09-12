package uday.drawoveranother;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends AppCompatActivity {


    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });


//        sendNotification("Click here to Cancel ");

//        ShowNotification("Click here to Cancel ");

    }

    public void show(View v){
        if(!isServiceRunning()) {
            startService(new Intent(SplashActivity.this, DrawOn.class));
        }
        ShowNotification("Click here to Cancel ",false);

    }
    public void hide(View v){
            Intent stopTrackMapService = new Intent(SplashActivity.this, DrawOn.class);
            stopService(stopTrackMapService);

        ShowNotification("Click here to Cancel ",true);
    }

    public void screenLockOn(View v){
        if(!isServiceRunning()) {
            startService(new Intent(SplashActivity.this, LockScreenService.class));
        }

//        ShowNotification("Screen Lock",false);
    }
    public void screenLockOff(View v){
        Intent stopTrackMapService = new Intent(SplashActivity.this, LockScreenService.class);
        stopService(stopTrackMapService);

//        ShowNotification("Click here to Cancel ",true);
    }



    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if("DrawOn".equals(service.service.getClassName())) {

                Log.e(TAG, "isServiceRunningLatlng: True" );
                return true;


            }
        }
        Log.e(TAG, "isServiceRunningLatlng: False" );
        return false;

    }

    private boolean isLOckServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if("LockScreenService".equals(service.service.getClassName())) {

                Log.e(TAG, "isServiceRunningLatlng: True" );
                return true;


            }
        }
        Log.e(TAG, "isServiceRunningLatlng: False" );
        return false;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private void sendNotification(String message) {
//        Intent intent = new Intent(this, SplashActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Screen Locked")
//                .setContentText(message)
//                .setAutoCancel(false)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//    }


    public void ShowNotification(String message,boolean can_state) {
        // notification icon
        int icon = R.mipmap.ic_launcher;
//	        above jellybean
        int icon_tran = R.mipmap.ic_launcher;
        String title=getString(R.string.app_name);

        Intent notificationIntent;
        long when = System.currentTimeMillis();

//	            Drawable d=mContext.getDrawable(R.drawable.notify);
//	            Bitmap icon_bitmap = ((BitmapDrawable)d).getBitmap();
        Bitmap icon_bitmap = BitmapFactory.decodeResource(SplashActivity.this.getResources(), R.mipmap.ic_launcher);

        notificationIntent = new Intent(SplashActivity.this, SplashActivity.class);

        Log.e("Gcm","From Gcm intent to Pushnotf");

//        Intent stopTrackMapService = new Intent(SplashActivity.this, LockScreenService.class);
//        stopService(stopTrackMapService);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        SplashActivity.this,
                        0,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                SplashActivity.this);
        Notification notification = mBuilder.setSmallIcon(icon).setTicker(title)

                .setContentTitle(title)
                .setStyle(inboxStyle)
                .setContentIntent(resultPendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), icon))
                .setWhen(System.currentTimeMillis())
                .setContentText(message)
                .setAutoCancel(false)
                .setOngoing(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(5, notification);

        if(can_state)
        notificationManager.cancelAll();

    }


}
