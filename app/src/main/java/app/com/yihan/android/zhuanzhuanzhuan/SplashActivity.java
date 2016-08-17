package app.com.yihan.android.zhuanzhuanzhuan;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler;

    Runnable start = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler = new Handler();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(start, 4000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(start);
    }
}
