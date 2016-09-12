package uday.drawoveranother;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Lakki on 01-Jul-16.
 */
public class DrawOn extends Service {

    private WindowManager windowManager;
    private ImageView chatHead;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    private LinearLayout main_ll;
//    LinearLayout child_ll;
    ObjectAnimator anim;

    int fontsize = 40;

    int tt1 = 4;
    int tt2 = 8;
    int tt3 = 6;
    int tt4 = 10;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        chatHead = new ImageView(this);

        t1 = new TextView(this);
        t2 = new TextView(this);
        t3 = new TextView(this);
        t4 = new TextView(this);

        chatHead.setImageResource(R.mipmap.ic_launcher);

        t1.setText("U");
        t2.setText("D");
        t3.setText("A");
        t4.setText("Y");

        t1.setTextSize(fontsize);
        t2.setTextSize(fontsize);
        t3.setTextSize(fontsize);
        t4.setTextSize(fontsize);

        t1.setTextColor(Color.BLUE);
        t2.setTextColor(Color.BLUE);
        t3.setTextColor(Color.BLUE);
        t4.setTextColor(Color.BLUE);
        t4.setTextColor(Color.BLUE);


        main_ll = new LinearLayout(this);
//        child_ll = new LinearLayout(this);
//        main_ll.setLayoutParams(new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
        main_ll.setOrientation(LinearLayout.VERTICAL);
//        child_ll.setOrientation(LinearLayout.HORIZONTAL);
//        child_ll.setLayoutParams(new LinearLayout.LayoutParams(500,500));
//        main_ll.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//        main_ll.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

//        final WindowManager.LayoutParams params2 = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                PixelFormat.TRANSLUCENT);

                final WindowManager.LayoutParams params2 = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG,
                PixelFormat.TRANSLUCENT);




        params2.gravity = Gravity.CENTER ;
//        params2.x = 0;
//        params2.y = 100;

//        Animation scale_2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
//        t2.startAnimation(scale_2);


//        main_ll.addView(chatHead);

//        windowManager.addView(chatHead, params);



        anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipimg1);
        anim.setTarget(t1);
        anim.setDuration(tt1*1000);
        anim.start();

        anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipimg1);
        anim.setTarget(t2);
        anim.setDuration(tt2*1000);
        anim.start();

        anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipimg1);
        anim.setTarget(t3);
        anim.setDuration(tt3*1000);
        anim.start();

        anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipimg1);
        anim.setTarget(t4);
        anim.setDuration(tt4*1000);
        anim.start();


        Log.e("Random", "onCreate: "+randomNo() );

        main_ll.addView(t1);
        main_ll.addView(t2);
        main_ll.addView(t3);
        main_ll.addView(t4);

//        child_ll.addView(t1);

//        main_ll.addView(t1);

        windowManager.addView(main_ll,params2);


        main_ll.setOnTouchListener(new View.OnTouchListener() {

             int initialX = 0;
            int initialY= 0;
            float initialTouchX= 0;
            float initialTouchY= 0;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(main_ll, params);
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        if (chatHead != null) windowManager.removeView(chatHead);
        if (chatHead != null) windowManager.removeView(main_ll);
    }

    public void pageRotate1(View view){
//        anim = (ObjectAnimator) AnimatorInflater.loadAnimator(PageturndemoActivity.this, R.animator.flipping1);
//        anim.setTarget(pageturn_demo_ll_1);
//        anim.setDuration(dur);
//        anim.start();
    }

    public int randomNo(){


        Random r = new Random();
        int Low = 1;
        int High = 9;
        int result = r.nextInt(High-Low) + Low;

        Log.e("Method", "randomNo: "+result );
        return result;
    }
}
