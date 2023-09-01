package com.example.prorate;



import static com.example.prorate.ListAdapter.getCurrentClass;

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

public class CommentActivity extends Activity
{
    private RecyclerView eventRV;


    // Arraylist for storing data
    private ArrayList<ListModelComments> eventModelArrayList;
    private DBCommsHandler commentHandler;
    private ArrayList<Comms> array;
    private TextView event;
    private Button notInterestedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_list);
        eventRV = findViewById(R.id.idRVEvent);
        commentHandler= new DBCommsHandler(CommentActivity.this);
        array = commentHandler.readComms();


        eventModelArrayList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            Comms aug=array.get(i);
            if(aug.getMaterie().equals(getCurrentClass())) {
                eventModelArrayList.add(new ListModelComments(aug.getCreator(), aug.getDescriere(), aug.getNota(), aug));
            }
        }

        ListAdapterComments courseAdapter = new ListAdapterComments(this, eventModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        eventRV.setLayoutManager(linearLayoutManager);
        eventRV.setAdapter(courseAdapter);
        event=findViewById(R.id.pageTtitle);
        event.setText(getCurrentClass());
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CommentActivity.this, CommentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

}
