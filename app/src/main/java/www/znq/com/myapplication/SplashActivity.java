package www.znq.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import www.znq.com.myapplication.extendss.MainActivity;
import www.znq.com.myapplication.proxy.ProxyModeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    //继承方式
    public void clickExtend(View view){
         startActivity(new Intent(this, MainActivity.class));
    }

    //proxy方式
    public void clickProxy(View view){
        startActivity(new Intent(this, ProxyModeActivity.class));
    }
}
