package com.example.prorate;

import static com.example.prorate.MainActivity.getCurrentUser;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barbura_iulia.R;

public class ProfileSettingsActivity extends AppCompatActivity {
    private Button saveChangesButton, deleteUserButton;
    private EditText firstName,lastName,username,facultate,an,sectie,password,confirmPassword;
    private DBUsersHander dbHandler;

    private Users aug,currentUserProf;
    public static String currentUser,encrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_settings);
        saveChangesButton = findViewById(R.id.saveChangesButton);
        deleteUserButton =findViewById(R.id.deleteUserButton);
        dbHandler = new DBUsersHander(ProfileSettingsActivity.this);
        firstName=findViewById(R.id.editFistName);
        lastName=findViewById(R.id.LastName);

        facultate=findViewById(R.id.editFacultate);
        an=findViewById(R.id.editAn);
        sectie=findViewById(R.id.editSectie);
        password=findViewById(R.id.editPassword);
        confirmPassword=findViewById(R.id.editCofirmPassword);
        String currentUser= getCurrentUser();
        aug=dbHandler.getUserDB(currentUser);
        firstName.setText(aug.getFirstName());
        lastName.setText(aug.getLastName());
        facultate.setText(aug.getFacultate());
        an.setText(aug.getAn().toString());
        sectie.setText(aug.getSpecializare());
        //System.out.println("Status"+aug.getStatus()+"+"+aug.getAn());
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                aug.setAn(Integer.parseInt(an.getText().toString()));
                aug.setFirstName(lastName.getText().toString());
                aug.setLastName(firstName.getText().toString());
                aug.setFacultate(facultate.getText().toString());
                aug.setSpecializare(sectie.getText().toString());
                if(password.getText().toString().equals(confirmPassword.getText().toString())&&!password.getText().toString().isEmpty()) {
                    try {
                        encrypt=AESUtils.encrypt(password.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    aug.setPassword(encrypt);

                    dbHandler.updateUser(aug);
                    Intent intent = new Intent(ProfileSettingsActivity.this, MainPage.class);// New activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(ProfileSettingsActivity.this, "Parolele nu corespund", Toast.LENGTH_SHORT).show();
                }
                else{

                    dbHandler.updateUser(aug);
                    Intent intent = new Intent(ProfileSettingsActivity.this, MainPage.class);// New activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileSettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Deletion");
                builder.setMessage("Are you sure you want to delete your profile?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHandler.deleteUser(getCurrentUser());

                                Intent intent = new Intent(ProfileSettingsActivity.this, MainActivity.class);// New activity
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        if(aug.getStatus()==2)
        {
            //facultate.setEnabled(false);
            an.setEnabled(false);
            an.setText("0");
            sectie.setEnabled(false);
            sectie.setText("---");
        }

    }
}