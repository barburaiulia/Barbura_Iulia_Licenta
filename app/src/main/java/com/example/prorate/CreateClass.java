package com.example.prorate;

import static com.example.prorate.MainActivity.getCurrentUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbura_iulia.R;

public class CreateClass extends AppCompatActivity {



    EditText eTitle,eDescription;
    Button btnCreate;

    private DBMateriiHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_class);
        dbHandler = new DBMateriiHandler(CreateClass.this);
        btnCreate=findViewById(R.id.buttonAddClass);
        eTitle = findViewById(R.id.editTextTitle);
        eDescription=findViewById(R.id.editTextDescription);
        btnCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = eTitle.getText().toString();

                String description=eDescription.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(CreateClass.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                String owner=getCurrentUser();
                Classes classes=new Classes(owner,title,(float) 0,description);
                dbHandler.addNewClass(classes);


                Intent intent = new Intent(CreateClass.this, MainPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


            }
        });


    }
}
