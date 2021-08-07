package jp.ac.jec.ws.p3_splashtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSplashScreen();
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        Button buttonSkip = findViewById(R.id.buttonSkip);
        Button buttonNext = findViewById(R.id.buttonNext);
        SharedPreferences sharedPreferences = getSharedPreferences("firstLogin",MODE_PRIVATE);

        adapter = new TutorialViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        currentPage = 0;

        if (sharedPreferences.getBoolean("DidFirstLogin",false)){
            goZodiacActivity();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("DidFirstLogin",true);
            editor.apply();
        }

            buttonNext.setOnClickListener(v -> {
                currentPage++;

                if (currentPage >= 2){
                    buttonNext.setText("END");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            goZodiacActivity();
                        }
                    },500);

                }

                viewPager.setCurrentItem(currentPage);
            });

            buttonSkip.setOnClickListener(v -> {
                goZodiacActivity();
            });

    }

    private void goZodiacActivity() {
        Intent intent = new Intent(MainActivity.this,ZodiacActivity.class);
        startActivity(intent);
    }

    /**
     * Splash画面の表示
     */
    private void setSplashScreen() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
