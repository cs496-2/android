package com.example.cs496_2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class LoginActivity extends Activity {

    private Button google_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        google_login = findViewById(R.id.btn_login_google);
        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                // todo: kakao login
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://kauth.kakao.com/oauth/authorize?client_id=16c296c98e32e8be9acbc9f9a4d0bc85&redirect_uri=http://127.0.0.1:3000/auth/kakao/redirect&response_type=code"));
//                startActivity(browserIntent);
            }
        });
    }
}
