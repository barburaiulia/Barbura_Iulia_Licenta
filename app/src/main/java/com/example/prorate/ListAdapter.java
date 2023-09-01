package com.example.prorate;




import static com.example.prorate.MainActivity.getCurrentUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbura_iulia.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Viewholder> implements SwipeFlingAdapterView.OnItemClickListener {

    private final Context context;
    private final ArrayList<ListModel> classModelArrayList;
    private DBMateriiHandler dbEventHandler;
    private DBUsersHander dbUsersHander;
    // Constructor
    public ListAdapter(Context context, ArrayList<ListModel> courseModelArrayList) {
        this.context = context;
        this.classModelArrayList = courseModelArrayList;
    }
    public static String getCurrentClass() {
        return currentClass;
    }

    public static void setCurrentClass(String currentClass) {
        ListAdapter.currentClass = currentClass;
    }

    public static String currentClass;
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        ListModel model = classModelArrayList.get(position);
        holder.classTitle.setText(model.getCardTitle());
        holder.classRating.setRating(model.getCardRating());
        holder.classDescription.setText(model.getCardDescription());
        dbUsersHander = new DBUsersHander((Activity)context);
        Users currentUser=dbUsersHander.getUserDB(getCurrentUser());
        System.out.println(currentUser.getStatus());
        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentClass(model.getCardTitle());
                Intent intent = new Intent( ((Activity)context), Comment.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ((Activity)context).startActivity(intent);

            }



        });
        holder.viewCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentClass(model.getCardTitle());
                Intent intent = new Intent( ((Activity)context), CommentActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ((Activity)context).startActivity(intent);
                /*String token=model.getEvent().getToken();
                String currUser=getCurrentUser();
                dbEventHandler= new DBMateriiHandler(context);
                Toast.makeText(context, "Eveniment elemininat", Toast.LENGTH_SHORT).show();
                participants_new.remove(currUser
                );
                dbEventHandler.updateParticipants(participants_new,token);*/


            }



        });
        if(currentUser.getStatus()==0||currentUser.getStatus()==2){
            holder.commentButton.setEnabled(false);
            holder.commentButton.setTextColor(Color.parseColor("#FFFF00"));

        }

    }

    @Override
    public int getItemCount() {

        return classModelArrayList.size();
    }

    @Override
    public void onItemClicked(int i, Object o) {

    }


    public class Viewholder extends RecyclerView.ViewHolder {

        private final TextView classTitle;
        private final RatingBar classRating;
        private final TextView classDescription;
        private final Button commentButton;
        private final Button viewCommentButton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            classTitle = itemView.findViewById(R.id.classTitle);
            classRating = itemView.findViewById(R.id.ratingBar);
            classDescription = itemView.findViewById(R.id.clasDescription);
            commentButton=itemView.findViewById(R.id.commentButton);
            viewCommentButton=itemView.findViewById(R.id.viewCommentButton);
        }
    }
}