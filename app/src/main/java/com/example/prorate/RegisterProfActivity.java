package com.example.prorate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbura_iulia.R;

import java.util.ArrayList;

public class RegisterProfActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText numeEdt,prenumeEdt,userNameEdt,passwordEdt,passwordVerEdt,facultateEdt,invitatieEdt,specialitatiEdt;
    private Button registerButton;
    private DBUsersHander dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_prof);

        // initializing all our variables.
        numeEdt = findViewById(R.id.idNumeTxt);
        prenumeEdt = findViewById(R.id.idPrenumeTxt);
        userNameEdt = findViewById(R.id.idUserNameTxt);
        passwordEdt = findViewById(R.id.idPasswordTxt);
        passwordVerEdt = findViewById(R.id.idPasswordTxtVer);
        registerButton = findViewById(R.id.idRegisterButton);
        facultateEdt=findViewById(R.id.idFacultateText);
        invitatieEdt=findViewById(R.id.idInvitatieText);
        //specialitatiEdt=findViewById(R.id.idSpecializareText);

        dbHandler = new DBUsersHander(RegisterProfActivity.this);
        ArrayList<Users> usersArrayList= dbHandler.readUsers();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nume = numeEdt.getText().toString();
                String prenume = prenumeEdt.getText().toString();
                String username = userNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                String passwordVer = passwordVerEdt.getText().toString();
                String facultate = facultateEdt.getText().toString();



                ArrayList<String> materii=new ArrayList<String>();
                if (nume.isEmpty() || prenume.isEmpty() || username.isEmpty() || password.isEmpty()||facultate.isEmpty()) {
                    Toast.makeText(RegisterProfActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!invitatieEdt.getText().toString().equals("*42")){
                    Toast.makeText(RegisterProfActivity.this, "Cod invitatie invalid", Toast.LENGTH_SHORT).show();
                    return;
                }



                if(!password.equals(passwordVer))
                {
                    Toast.makeText(RegisterProfActivity.this, "Parolele nu corespund", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(Users us : usersArrayList) {
                    if (us.getUsername().equals(username) ) {
                        Toast.makeText(RegisterProfActivity.this, "User-ul deja exista", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                try {
                    password=AESUtils.encrypt(password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                dbHandler.addNewUser(nume, prenume, username, password,facultate,0,"-",2,materii);


                    Toast.makeText(RegisterProfActivity.this, "Inregistrare cu succes.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterProfActivity.this, MainActivity.class);// New activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

            }
        });
    }
}
