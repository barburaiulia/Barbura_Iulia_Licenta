package com.example.prorate;



import static com.example.prorate.MainActivity.getCurrentUser;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbura_iulia.R;

import java.util.ArrayList;

public class ClassesActivity extends Activity
{
    private RecyclerView eventRV;


    // Arraylist for storing data
    private ArrayList<ListModel> eventModelArrayList;
    private DBMateriiHandler classesHandler;
    private DBUsersHander userHandler;
    private ArrayList<Classes> array;
    private Users users;
    private TextView event;
    private Button notInterestedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classes_activity);
        eventRV = findViewById(R.id.idRVEvent);
        classesHandler= new DBMateriiHandler(ClassesActivity.this);
        array = classesHandler.readClasses();
        userHandler=new DBUsersHander(ClassesActivity.this);
        users=userHandler.getUserDB(getCurrentUser());
        ArrayList<String> materii=users.getMaterii();

        eventModelArrayList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            Classes aug = array.get(i);
            if(users.getStatus()==1){
            if (materii.contains(aug.getMaterie())) {
                eventModelArrayList.add(new ListModel(aug.getMaterie(), aug.getDescriere(), aug.getNota(), aug));

            }
            }
            else {
                eventModelArrayList.add(new ListModel(aug.getMaterie(), aug.getDescriere(), aug.getNota(), aug));
            }
        }
        ListAdapter courseAdapter = new ListAdapter(this, eventModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        eventRV.setLayoutManager(linearLayoutManager);
        eventRV.setAdapter(courseAdapter);
        event=findViewById(R.id.textView14);

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClassesActivity.this, ClassesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

}
