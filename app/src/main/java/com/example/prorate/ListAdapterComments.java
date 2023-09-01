package com.example.prorate;




import static com.example.prorate.MainActivity.getCurrentUser;

import android.content.Context;
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

public class ListAdapterComments extends RecyclerView.Adapter<ListAdapterComments.Viewholder> implements SwipeFlingAdapterView.OnItemClickListener {

    private final Context context;
    private final ArrayList<ListModelComments> classModelArrayList;
    private DBMateriiHandler dbEventHandler;
    // Constructor
    public ListAdapterComments(Context context, ArrayList<ListModelComments> courseModelArrayList) {
        this.context = context;
        this.classModelArrayList = courseModelArrayList;
    }
    public static String getCurrentClass() {
        return currentClass;
    }

    public static void setCurrentClass(String currentClass) {
        ListAdapterComments.currentClass = currentClass;
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

        ListModelComments model = classModelArrayList.get(position);
        holder.classTitle.setText(model.getCardTitle());
        holder.classRating.setRating(model.getCardRating());
        holder.classDescription.setText(model.getCardDescription());

        holder.commentButton.setEnabled(false);
        holder.commentButton.setVisibility(View.INVISIBLE);

        holder.viewCommentButton.setEnabled(false);
        holder.viewCommentButton.setVisibility(View.INVISIBLE);

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