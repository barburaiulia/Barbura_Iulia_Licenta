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

public class RegisterActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText numeEdt,prenumeEdt,userNameEdt,passwordEdt,passwordVerEdt,facultateEdt,anEdt,specialitatiEdt;
    private Button registerButton;
    private DBUsersHander dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // initializing all our variables.
        numeEdt = findViewById(R.id.idNumeTxt);
        prenumeEdt = findViewById(R.id.idPrenumeTxt);
        userNameEdt = findViewById(R.id.idUserNameTxt);
        passwordEdt = findViewById(R.id.idPasswordTxt);
        passwordVerEdt = findViewById(R.id.idPasswordTxtVer);
        registerButton = findViewById(R.id.idRegisterButton);
        facultateEdt=findViewById(R.id.idFacultateText);
        anEdt=findViewById(R.id.idAnText);
        specialitatiEdt=findViewById(R.id.idSpecializareText);

        dbHandler = new DBUsersHander(RegisterActivity.this);
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
                String an_aug=anEdt.getText().toString();
                Integer an=Integer.parseInt(an_aug);
                String specialate= specialitatiEdt.getText().toString();
                ArrayList<String> materii=new ArrayList<String>();
                if (nume.isEmpty() || prenume.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!password.equals(passwordVer))
                {
                    Toast.makeText(RegisterActivity.this, "Parolele nu corespund", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(Users us : usersArrayList) {
                    if (us.getUsername().equals(username) ) {
                        Toast.makeText(RegisterActivity.this, "User-ul deja exista", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                try {
                    password=AESUtils.encrypt(password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                dbHandler.addNewUser(nume, prenume, username, password,facultate,an,specialate,1,materii);


                    Toast.makeText(RegisterActivity.this, "Inregistrare cu succes.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);// New activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

            }
        });
    }
}
