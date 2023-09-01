package com.example.prorate;

import static com.example.prorate.ListAdapter.getCurrentClass;
import static com.example.prorate.MainActivity.getCurrentUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbura_iulia.R;


public class Comment extends AppCompatActivity {



    EditText eDescription;
    TextView eTitle;
    RatingBar ratingBar;
    Button btnCreate;
    String oldDescription;
    private DBCommsHandler dbHandler;
    private DBMateriiHandler dbHandlerClass;
    boolean exist=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.comment);
        dbHandler = new DBCommsHandler(Comment.this);
        dbHandlerClass = new DBMateriiHandler(Comment.this);

        btnCreate=findViewById(R.id.buttonComment);
        eTitle = findViewById(R.id.textView12);
        eTitle.setText(getCurrentClass());
        ratingBar=findViewById(R.id.ratingBar);
        eDescription=findViewById(R.id.editTextDescription);
        if(dbHandler.existComm(getCurrentUser(),getCurrentClass())){
            Comms aug=dbHandler.getComm(getCurrentUser(),getCurrentClass());
            //System.out.println(aug.getCreator()+"  "+aug.getDescriere());
            oldDescription= aug.getDescriere();
            eDescription.setText(aug.getDescriere());
            ratingBar.setRating(aug.getNota());
            exist=true;
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = eTitle.getText().toString();
                Float rating=ratingBar.getRating();
                String description=eDescription.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(Comment.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                String owner=getCurrentUser();
                Comms comms=new Comms(owner,title,rating,description);
                if(exist)
                {
                    dbHandler.updateComms(comms,oldDescription);
                }
                else {
                    dbHandler.addNewComm(comms);
                }
                dbHandlerClass.updateRating(dbHandler.averageRating(title),title);

                Intent intent = new Intent(Comment.this, MainPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


            }
        });


    }
}
