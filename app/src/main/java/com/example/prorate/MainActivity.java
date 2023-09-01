package com.example.prorate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbura_iulia.R;

public class MainActivity extends AppCompatActivity {
    private Button registerButton, loginButton;
    private DBUsersHander dbHandler;

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        MainActivity.currentUser = currentUser;
    }

    public static String currentUser="Dummy";

    private Button letsStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_page);
        letsStart=findViewById(R.id.letsStartButton);
        letsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish(); // Call once you redirect to another activity

            }
        });

    }

}

