package com.example.prorate;

import static com.example.prorate.MainActivity.getCurrentUser;
import static com.example.prorate.MainActivity.setCurrentUser;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbura_iulia.R;

public class MainPage extends AppCompatActivity {
    private ImageButton enrollButton, viewSubjectsButton,editProfile,addClassButton,logOutButton;
    private DBUsersHander dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DBUsersHander(MainPage.this);
        Users currentUser=dbHandler.getUserDB(getCurrentUser());


        setContentView(R.layout.main_page);
        enrollButton=findViewById(R.id.enrollmentButton);
        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, EnrollmentActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish(); // Call once you redirect to another activity

            }
        });
        addClassButton=findViewById(R.id.addClassButton);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, CreateClass.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish(); // Call once you redirect to another activity

            }
        });
        viewSubjectsButton=findViewById(R.id.classesButton);
        viewSubjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, ClassesActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish(); // Call once you redirect to another activity

            }
        });
        editProfile=findViewById(R.id.profileSettingButton);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, ProfileSettingsActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish(); // Call once you redirect to another activity

            }
        });
        editProfile=findViewById(R.id.profileSettingButton);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, ProfileSettingsActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish(); // Call once you redirect to another activity

            }
        });
        logOutButton=findViewById(R.id.logOut);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentUser("Dummy");
                Intent intent = new Intent(MainPage.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish(); // Call once you redirect to another activity

            }
        });

        if(currentUser.getStatus()==1){
            addClassButton.setEnabled(false);
            addClassButton.setBackgroundTintMode(PorterDuff.Mode.ADD);
            addClassButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6AFFF5F5")));
        }
        else {
            enrollButton.setEnabled(false);
            enrollButton.setBackgroundTintMode(PorterDuff.Mode.ADD);
            enrollButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6AFFF5F5")));
        }
    }

}
